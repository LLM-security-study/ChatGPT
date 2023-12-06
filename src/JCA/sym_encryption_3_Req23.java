import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req23 {
  public static void main(String[] args) throws Exception {
    // Generating a new AES key
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();

    // Encrypt data
    String plainText = "Hello, World!";
    Cipher encryptCipher = Cipher.getInstance("AES");
    encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = encryptCipher.doFinal(plainText.getBytes());

    // Convert to base64 for easy understanding and visibility
    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    System.out.println("Encrypted Text: " + encryptedText);
    
    // Decrypt data
    Cipher decryptCipher = Cipher.getInstance("AES");
    decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
    
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted Text: " + decryptedText);
  }
}