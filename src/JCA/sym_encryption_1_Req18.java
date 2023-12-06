import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class sym_encryption_1_Req18 {
    private static SecretKeySpec secretKey;
    private static final String secret = "ssdkF$HUy2A#D%kd";

    static {
        secretKey = new SecretKeySpec(secret.getBytes(), "AES");
    }
    
    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        final String originalString = "Hello, World!";

        System.out.println("Original string: " + originalString);

        String encryptedString = sym_encryption_1_Req18.encrypt(originalString);

        System.out.println("Encrypted string: " + encryptedString);

        String decryptedString = sym_encryption_1_Req18.decrypt(encryptedString);

        System.out.println("Decrypted string: " + decryptedString);
    }
}