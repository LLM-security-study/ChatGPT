import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_2_Req10 {
    public static void main(String[] args) {
        try {
            // Generate Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // Key size
            SecretKey secretKey = keyGenerator.generateKey();

            // Text to be encrypted
            String plainText = "Hello, World!";

            // Encryption
            Cipher encryptCipher = Cipher.getInstance("AES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedText = encryptCipher.doFinal(plainText.getBytes());

            System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(encryptedText));

            // Decryption
            SecretKeySpec originalKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
            Cipher decryptCipher = Cipher.getInstance("AES");
            decryptCipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] decryptedText = decryptCipher.doFinal(encryptedText);

            System.out.println("Decrypted Text: " + new String(decryptedText));
        } catch (Exception e) {
            System.out.println("There was an error during encryption/decryption");
            e.printStackTrace();
        }
    }
}