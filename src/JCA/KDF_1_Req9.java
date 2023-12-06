import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class KDF_1_Req9 {

    public static final String SALT = "ssshhhhhhhhhhh!!!!"; // Salt should be kept secret and should have been securely shared between parties who have the password

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        String password = "Your Password"; // Replace with user's actual password
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        System.out.println(Base64.getEncoder().encodeToString(secret.getEncoded()));
        
        // For encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] encryptedData = cipher.doFinal("PlainText".getBytes());
        
        // For decryption
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        
        System.out.println(new String(decryptedData));
    }
}