import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_3_Req14 {
    public static void main(String[] args) throws Exception {
        
        // Generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // The original message
        String message = "The quick brown fox";
        System.out.println("Original Message : " + message);

        // Encrypt
        Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = encryptCipher.doFinal(message.getBytes());

        // Convert to string and print to console
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encoded Message : " + encodedMessage);

        // Decrypt
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = decryptCipher.doFinal(encryptedMessage);

        System.out.println("Decrypted Message : " + new String(decryptedMessage));
    }
}