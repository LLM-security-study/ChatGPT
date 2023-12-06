import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req30 {

  public static void main(String[] args) throws Exception {
    String plainText = "Hello! This is a sample message.";

    // Generate secret key for AES encryption
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();

    // Get Cipher instance and initialize it to encrytion mode
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    System.out.println("Encrypted Text: " + encryptedText);

    // Initialize the same cipher for decryption
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

    System.out.println("Decrypted Text: " + decryptedText);
  }

}