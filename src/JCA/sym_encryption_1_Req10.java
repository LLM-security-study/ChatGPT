import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req10 {
    public static void main(String[] args) throws Exception {
        // The plain text to be encrypted
        String plainText = "Hello World!";

        // Generate the symmetric AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);  // Key size is specified here
        SecretKey secretKey = keyGenerator.generateKey();

        String encrypted = encrypt(plainText, secretKey);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, secretKey);
        System.out.println("Decrypted text: " + decrypted);
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}