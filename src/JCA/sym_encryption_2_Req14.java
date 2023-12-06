import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_2_Req14 {
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        
        String key = "asimple128bitkey"; // The key should be of length 16 for AES
        String text = "Hello, World!";
        String encryptedText = encrypt(text, key);
        String decryptedText = decrypt(encryptedText, key);
        
        // Test and print results:
        System.out.println("Original text: " + text);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String encrypt(String data, String key) {
        // This will be put in a try block to handle the potential exceptions
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Return text encrypted in base64 format
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            // Handle the exceptions accordingly
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String encryptedData, String key) {

        try {
            Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decrypt the base64 formatted text
            byte[] decipheredText = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            return new String(decipheredText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}