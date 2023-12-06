import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_3_Req26 {
    public static void main(String[] args) throws Exception {
        // Generate secret key for AES algorithm
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // Get AES cipher
        Cipher cipher = Cipher.getInstance("AES");

        // Message to decrypt
        String message = "Hello, World!";
        System.out.println("Original message: " + message);

        // Encrypt the message
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Decrypt the message
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
        System.out.println("Decrypted message: " + new String(decryptedMessage));
    }
}