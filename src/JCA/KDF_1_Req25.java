import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class KDF_1_Req25 {

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        String password = "YourPassword";
        String salt = "YourSalt"; // Should be saved with the password, DO NOT LOSE
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, ITERATION_COUNT, KEY_LENGTH);
        String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

        System.out.println(hashedPassword);
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            return skf.generateSecret(spec).getEncoded();
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}