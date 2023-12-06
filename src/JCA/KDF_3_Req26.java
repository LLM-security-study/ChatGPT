import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.UUID;

public class KDF_3_Req26 {

    private static final int SALT_SIZE = 16;
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;

    public static void main(String[] args) {
        String password = "testPassword";

        try {
            // Generate Salt
            byte[] salt = generateSalt();

            // Create Key
            byte[] encryptedPassword = hashPassword(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);

            String saltString = Base64.getEncoder().encodeToString(salt);
            String passwordString = Base64.getEncoder().encodeToString(encryptedPassword);

            System.out.println("Salt: " + saltString);
            System.out.println("Encrypted Password: " + passwordString);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Exception Encountered: " + e);
        }
    }

    private static byte[] generateSalt() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().getBytes();
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        return skf.generateSecret(spec).getEncoded();
    }
}