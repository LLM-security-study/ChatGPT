import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_2_Req2 {

    public static final String ALGORITHM = "AES/CBC/PKCS5PADDING";

    public static void main(String[] args) {

        //Initial Vector and Key should be 16 bytes
        String initialVector = "abcdefg123456789"; //16 bytes
        String key = "abcdefg123456789"; //16 bytes
        String plainText = "This is a test string.";

        String encrypted = encrypt(plainText, key, initialVector);
        System.out.println("Encrypted : "+encrypted);

        String decrypted = decrypt(encrypted, key, initialVector);
        System.out.println("Decrypted : "+decrypted);
    }

    public static String encrypt(String data, String key, String initialVector) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted, String key, String initialVector) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}