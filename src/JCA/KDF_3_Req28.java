import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class KDF_3_Req28 {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        String password = "password";
        String salt = "salt";

        String encodedKey = getPBKDF2Key(password, salt);
        System.out.println("PBKDF2 key: " + encodedKey);
    }

    public static String getPBKDF2Key(String password, String salt) {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}