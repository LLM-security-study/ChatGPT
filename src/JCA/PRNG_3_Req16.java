import java.security.SecureRandom;

public class PRNG_3_Req16 {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate seed bytes
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);
        
        // Initialize SecureRandom with the generated seed
        SecureRandom secureRandomWithSeed = new SecureRandom(seed);
        
        // Generate a random integer
        int randomInteger = secureRandomWithSeed.nextInt();
        System.out.println("Generated random integer: " + randomInteger);
    }
}