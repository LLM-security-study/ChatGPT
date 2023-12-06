import java.security.SecureRandom;

public class PRNG_3_Req17 {

    public static void main(String[] args) {
        SecureRandom secureRandomGenerator = new SecureRandom();
        
        // Create seed generator
        byte[] randomBytes = new byte[64];
        secureRandomGenerator.nextBytes(randomBytes);
       
        // Get the seed 
        SecureRandom secureRandomSeed = new SecureRandom(randomBytes);
        
        // Generate a random integer
        int randomInt = secureRandomSeed.nextInt();
        
        System.out.println("Random integer: " + randomInt);
    }
}