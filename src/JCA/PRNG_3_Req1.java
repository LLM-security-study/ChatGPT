import java.security.SecureRandom;

public class PRNG_3_Req1 {
    public static void main(String[] args) {
        // Creates a secure random number generator
        SecureRandom secureRandom = new SecureRandom();

        // Get a random seed and set it
        byte[] seed = secureRandom.generateSeed(8);
        secureRandom.setSeed(seed);

        // Generate a random number
        int randomNumber = secureRandom.nextInt();
        System.out.println("Random number generated: " + randomNumber);
    }
}