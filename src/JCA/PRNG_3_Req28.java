import java.security.SecureRandom;

public class PRNG_3_Req28 {
    public static void main(String[] args) {
        SecureRandom secureRand = new SecureRandom();

        // Create a seed
        byte[] seed = new byte[20];
        secureRand.nextBytes(seed);

        // Re-seed the random number generator
        secureRand.setSeed(seed);

        // Generate a random int
        int randomInt = secureRand.nextInt();
        System.out.println("Random Integer: " + randomInt);
    }
}