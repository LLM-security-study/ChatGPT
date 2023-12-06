// Import required classes
import java.security.SecureRandom;

// Define main class
public class PRNG_3_Req23 {
    // Define main method
    public static void main(String[] args) {
        // Create SecureRandom instance
        SecureRandom secureRandom = new SecureRandom();

        // Generate seed
        byte[] seed = new byte[8];
        secureRandom.nextBytes(seed);

        // Initialize SecureRandom with the generated seed
        secureRandom.setSeed(seed);
        
        // Generate a random integer
        int randomInt = secureRandom.nextInt();

        // Print random integer
        System.out.println("Generated random integer is: " + randomInt);
    }
}