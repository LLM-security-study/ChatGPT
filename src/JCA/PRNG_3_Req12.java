import java.security.SecureRandom;

public class PRNG_3_Req12 {
    public static void main(String[] args) {
        // Create a secure random number generator using the "SHA1PRNG" algorithm
        SecureRandom secureRandom = null;

        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");

            // Generate a seed
            secureRandom.setSeed(secureRandom.generateSeed(8));

            // Generate a random integer
            int randomInteger = secureRandom.nextInt();

            // Print the generated integer
            System.out.println("Generated random integer: " + randomInteger);
        } catch (Exception e) {
            System.err.println("Exception caught: " + e);
        }
    }
}