import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.security.SecureRandom;

public class KDF_2_Req22 {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int KEY_SIZE = 256;
    private static final int SALT_SIZE = 256;
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        try {
            // Get password from user
            String password = "userPassword";  // replace with actual password

            // Generate salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_SIZE / 8];
            random.nextBytes(salt);

            // Create key
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_SIZE);
            SecretKey tmp = skf.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Print the secret key
            System.out.println(secretKey);

        } catch (Exception e) {
            System.out.println("Error generating secret key");
            e.printStackTrace();
        }
    }
}