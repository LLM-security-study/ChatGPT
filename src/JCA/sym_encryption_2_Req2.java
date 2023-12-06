import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req2 {
    public static void main(String[] args) throws Exception {
        // Generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Get Cipher instance and initialize it to encrypt mode
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the data
        String input = "plaintext";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(inputBytes);
        String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted data: " + encryptedData);

        // Initialize the same cipher for decryption. 
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the data
        byte[] outputBytes = cipher.doFinal(encryptedBytes);
        String decryptedData = new String(outputBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted data: " + decryptedData);
    }
}