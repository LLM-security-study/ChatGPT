import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_2_Req25 {
    public static void main(String[] args) throws Exception {
        
        // Generate a key first
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // key size: 128, 192 or 256 bits
        SecretKey secretKey = keyGenerator.generateKey();

        String plainText = "Hello, World!";

        // For encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(cipherText));

        // For decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(cipherText);
        System.out.println("Decrypted Text: " + new String(decryptedText));
    }
}