import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req18 {

    private static final String INIT_VECTOR = "abcdefghijklmnop"; // 16 bytes IV

    public static void main(String[] args) {
        String key = "ThisIsASecretKey"; // 16 bytes key 
        String originalString = "This is the original String";

        System.out.println("Original String: " + originalString);
        String encryptedString = encrypt(originalString, key);
        System.out.println("Encrypted String: " + encryptedString);
        String decryptedString = decrypt(encryptedString, key);
        System.out.println("Decrypted String: " + decryptedString);
    }

    public static String encrypt(String value, String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKey sk = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sk, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted, String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKey sk = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sk, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}