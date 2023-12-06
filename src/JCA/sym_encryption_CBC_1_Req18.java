import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;


public class sym_encryption_CBC_1_Req18 {
    private static byte[] iv = new byte[16];
    static {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
    }
    
    
    public static String encrypt(String plainText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText, String key) throws Exception {
        
        byte[] encryptedText = Base64.getDecoder().decode(cipherText);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] decryptedText = cipher.doFinal(encryptedText);

        return new String(decryptedText);
    }

    // Sampling function
    public static void main(String[] args) throws Exception {
        String key = "Bar12345Bar12345"; // 128 bit key example
        String input = "Hello World!";

        String encrypted = encrypt(input, key);
        System.out.println("Encrypted: " +encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}