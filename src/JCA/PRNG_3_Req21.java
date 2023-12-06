import java.util.Random;
import java.security.SecureRandom;

public class PRNG_3_Req21 {
    public static void main(String[] args) {
        // Generate a seed
        SecureRandom random = new SecureRandom();
        byte[] seed = random.generateSeed(20);

        // Initialize a new random object with the seed
        Random rand = new Random(new SecureRandom(seed).nextLong());

        // Generate a random integer
        int randInt = rand.nextInt();
        
        // Ensuring the random number is a positive value
        randInt = (randInt < 0) ? -randInt : randInt; 

        // Print the random integer
        System.out.println("Random Integer: " + randInt);
    }
}