import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req21 {
    public static void main(String[] args) throws Exception {
        // Generate a key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); 
        SecretKey secretKey = keyGenerator.generateKey();

        String originalString = "Hello, World!";

        // Encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(originalString.getBytes(StandardCharsets.UTF_8));
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted String: " + encryptedString);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted String: " + decryptedString);
    }
}