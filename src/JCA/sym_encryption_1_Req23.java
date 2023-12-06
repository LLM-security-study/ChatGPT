import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_1_Req23 {
    public static void main(String[] args) throws Exception {
        // Generate a key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Key size
        SecretKey key = keyGen.generateKey();

        // Input text
        String text = "Hello, World!";
        System.out.println("Input Text: " + text);

        // Encrypt
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);
        
        // Decrypt
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: "+ decryptedText);
    }

    private static String encrypt(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    private static String decrypt(String encryptedText, SecretKey key) throws Exception {
        byte[] cipherText = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] originalText = cipher.doFinal(cipherText);
        return new String(originalText);
    }
}