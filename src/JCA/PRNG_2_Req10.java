import java.security.*;
import java.util.*;

public class PRNG_2_Req10 {
    public static void main(String[] args) {
        try {
            // Get instance of SecureRandom
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            // Generate a random number seed
            byte[] seed = new byte[16];
            sr.nextBytes(seed);

            // Print the seed
            System.out.println("Generated seed: " + Arrays.toString(seed));

            // Initialize a new instance of SecureRandom with the seed
            SecureRandom random = new SecureRandom(seed);

            // Generate a random salt
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Print the salt
            System.out.println("Generated salt: " + Arrays.toString(salt));

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm not found: " + e);
        }
    }
}