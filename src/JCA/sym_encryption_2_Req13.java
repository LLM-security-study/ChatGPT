import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_2_Req13 {
    public static void main(String[] args) {

        try {
            // Generate a key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create a cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt some data
            String message = "This is a secret message!";
            byte[] plaintext = message.getBytes();
            byte[] ciphertext = cipher.doFinal(plaintext);

            // Print the original and encrypted texts
            System.out.println("Original Text: " + message);
            System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(ciphertext));

            // Decrypt the data back
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedCiphertext = cipher.doFinal(ciphertext);

            // Print the decrypted text
            System.out.println("Decrypted Text: " + new String(decryptedCiphertext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}