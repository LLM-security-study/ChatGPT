import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PRNG_3_Req24 {
    public static void main(String[] args) {

        SecureRandom secureRandom;
        try {
            // This is a cryptographically strong pseudo random number generator
            secureRandom = SecureRandom.getInstance("SHA1PRNG");

            // Generate a random seed
            byte[] seed = new byte[16];
            secureRandom.nextBytes(seed);

            // Initialize the internal state of the generator
            secureRandom.setSeed(seed);

            // Use the generator to produce a random int
            int randomInt = secureRandom.nextInt();

            System.out.println("Random Integer: " + randomInt);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm exists");
        }

    }
}