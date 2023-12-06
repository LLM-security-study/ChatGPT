import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class KDF_2_Req4 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {

        // Taking a password
        String password = "my-secret-pass";

        // Salt for password encryption, note that you should save salt for decryption
        byte[] salt = new byte[8];
        SecureRandom.getInstanceStrong().nextBytes(salt);

        // Iteration count
        int iterationCount = 1024;

        // Creating key
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        
        // Print details
        System.out.println("Password: " + password);
        System.out.println("Salt: " + new String(salt));
        System.out.println("Iteration Count: " + iterationCount);
        System.out.println("Secret Key: " + secretKey.toString());
    }
}