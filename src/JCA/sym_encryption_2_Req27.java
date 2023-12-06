import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req27 {

  public static void main(String[] args) {
     try {
        // Generate a key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();

        // Create a cipher instance
        Cipher aesCipher = Cipher.getInstance("AES");

        // Initialize the cipher for encryption
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the data
        String dataToEncrypt = "Hello, World!";
        byte[] byteDataToEncrypt = dataToEncrypt.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
        String strCipherText = Base64.getEncoder().encodeToString(byteCipherText);
        System.out.println("Cipher Text: " + strCipherText);

        // Initialize the same cipher for decryption
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the cipher text
        byte[] byteDecryptedText = aesCipher.doFinal(Base64.getDecoder().decode(strCipherText));
        String strDecryptedText = new String(byteDecryptedText);
        System.out.println("Decrypted Text: " + strDecryptedText);

     } catch (Exception e) {
        e.printStackTrace();
     }
  }
}