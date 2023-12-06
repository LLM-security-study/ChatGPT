import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class sym_encryption_CBC_1_Req30 {

    private static final String INIT_VECTOR = "RandomInitVector"; // 16 bytes
    private static final String SECRET_KEY = "MySecureKeyHere"; // 16 bytes

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec spec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, spec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec spec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, spec, iv);

            byte[] decoded = Base64.getDecoder().decode(encrypted);

            return new String(cipher.doFinal(decoded));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String originalString = "This is a secret message";
        System.out.println("Original string: " + originalString);
        
        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted string: " + encryptedString);
        
        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted string: " + decryptedString);
    }
}