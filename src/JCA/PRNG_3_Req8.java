import java.security.SecureRandom;

public class PRNG_3_Req8 {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        
        // Generate a random seed
        byte[] seed = new byte[512];
        sr.nextBytes(seed);
      
        // Initialize SecureRandom with the generated seed
        SecureRandom secureRandom = new SecureRandom(seed);
      
        // Generate a random integer
        int randomInt = secureRandom.nextInt();
      
        System.out.println("Random Integer: " + randomInt);
    }
}