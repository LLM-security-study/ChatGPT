import java.security.SecureRandom;

public class PRNG_3_Req30 {
    public static void main(String[] args) {
        // Create a secure random number generator using the "SHA1PRNG" algorithm
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate a seed byte array of 20 bytes
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);
        
        // Re-initialize the secure random number generator with the new seed
        secureRandom.setSeed(seed);
        
        // Generate a random integer 
        int randomNum = secureRandom.nextInt();

        // Print the random number
        System.out.println("Random Number: " + randomNum);
    }
}