import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req26 {

    public static void main(String[] args) throws Exception {
        //Generate key
        SecretKey secretKey = generateSecretKey();

        //Text to be encrypted
        String plainText = "I am a programming assistant!";

        //Encrypt text
        String encryptedText = encryptText(secretKey, plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        //Decrypt text
        String decryptedText = decryptText(secretKey, encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        return generator.generateKey();
    }

    public static String encryptText(SecretKey key, String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptText(SecretKey key, String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = Base64.getDecoder().decode(encryptedText);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}