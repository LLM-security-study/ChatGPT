import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req13 {

    // Size of the salt
    private static final int SALT_SIZE = 32;

    public static void main(String[] args) {
        try {
            // Create a secure random number generator
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            // Create a seed
            byte[] seed = sr.generateSeed(SALT_SIZE);
        
            // Set the seed for the secure random number generator
            sr.setSeed(seed);
        
            // Generate the salt
            byte[] salt = new byte[SALT_SIZE];
            sr.nextBytes(salt);

            System.out.println("Generated salt: " + Base64.getEncoder().encodeToString(salt));

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}