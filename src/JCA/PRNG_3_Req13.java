import java.security.SecureRandom;

public class PRNG_3_Req13 {
    public static void main(String[] args) {
        // Create a new instance of SecureRandom
        SecureRandom random = new SecureRandom();

        // Generate a new seed and set it.
        byte[] seed = new byte[20]; // Arbitrary size for seed
        random.nextBytes(seed); // Randomly fills the bytes
        random.setSeed(seed);

        // Generate a random integer
        int randomInt = random.nextInt();

        // Print out the random integer.
        System.out.println("Your generated random integer is: " + randomInt);
    }
}