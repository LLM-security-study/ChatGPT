import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class sym_encryption_CBC_2_Req28 {
    public static void main(String[] args) throws Exception {
        // Original text
        String originalText = "Text to encrypt";

        // Generate a random AES key
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Generate a random IV
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Initialize the Cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Encrypt
        byte[] ciphertext = cipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));

        // Convert to base64 
        String base64Ciphertext = Base64.getEncoder().encodeToString(ciphertext);

        // Print the encrypted text
        System.out.println("Encrypted Text: " + base64Ciphertext);
    }
}