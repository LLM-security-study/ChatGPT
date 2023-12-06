import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Arrays;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class KDF_3_Req2 {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final SecureRandom RAND = new SecureRandom();

    public static void main(String[] args) {
        String password = "myPassword";
        byte[] salt = generateSalt();

        try {
            byte[] hashedPassword = hashPassword(password.toCharArray(), salt);
            System.out.println(Arrays.toString(hashedPassword));
        } catch (Exception ex) {
            System.err.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        RAND.nextBytes(salt);
        return salt;
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(spec).getEncoded();
    }
}