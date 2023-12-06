import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req4 {

    public static void main(String[] args) {
        // Generate a seed to initialize a pseudorandom number generator
        SecureRandom seedGenerator = new SecureRandom();
        byte[] seed = new byte[10];
        seedGenerator.nextBytes(seed);

        // Use the seed to initialize a new pseudorandom number generator
        SecureRandom prng = new SecureRandom(seed);

        // Generate the salt for password hashing
        byte[] salt = new byte[16];
        prng.nextBytes(salt);

        // Print out the generated salt
        System.out.println("Generated Salt: " + Base64.getEncoder().encodeToString(salt));
    }
}