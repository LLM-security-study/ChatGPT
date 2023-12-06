import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req18 {
    public static void main(String[] args) throws Exception {
        // Generate a secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Key size is specified here. It could be 128, 192, or 256 bits.
        SecretKey secretKey = keyGenerator.generateKey();

        // Original message
        String originalMessage = "Hello, World!";

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(originalMessage.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String decryptedMessage = new String(cipher.doFinal(encryptedMessage), StandardCharsets.UTF_8);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}