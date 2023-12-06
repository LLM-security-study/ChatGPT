import java.security.SecureRandom;

public class PRNG_3_Req11 {

    public static void main(String[] args) {

        // Create a SecureRandom instance
        SecureRandom secRand = new SecureRandom();

        // Generate a random seed
        byte[] seed = new byte[16];
        secRand.nextBytes(seed);

        // Re-initialize the SecureRandom instance with the generated seed
        secRand.setSeed(seed);

        // Generate a random integer
        int randInt = secRand.nextInt();

        System.out.println("Random integer: " + randInt);
    }
}