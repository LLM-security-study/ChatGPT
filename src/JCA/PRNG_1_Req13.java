import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_1_Req13 {
    public static void main(String[] args) {
        // Create secure random seed
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = new byte[16];
        secureRandom.nextBytes(seed);

        System.out.println("Seed: " + Base64.getEncoder().encodeToString(seed));

        // Initialize the secure random with the generated seed
        SecureRandom generator = new SecureRandom(seed);
        
        // Generate Initialization Vector (IV)
        byte[] iv = new byte[16];  
        generator.nextBytes(iv);

        System.out.println("IV: " + Base64.getEncoder().encodeToString(iv));
    }
}