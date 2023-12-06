import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_1_Req30 {
    public static void main(String[] args) throws Exception {
        String text = "This is a sample text";
        SecretKey key = generateKey("AES");
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original text: " + text);
        System.out.println("Encrypted text: " + encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }

    public static SecretKey generateKey(String algorithm) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decrypted);
    }
}