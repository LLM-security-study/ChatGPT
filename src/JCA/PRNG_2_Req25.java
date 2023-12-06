import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req25 {
    public static void main(String[] args) {
        // Create SecureRandom instance
        SecureRandom sr = new SecureRandom();
        
        // Create byte array
        byte[] seed = new byte[16];
        
        // Get random seed
        sr.nextBytes(seed);
        
        // Initialize SecureRandom with the generated seed
        sr.setSeed(seed);
        
        // Generate salt
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        
        // Print salt in base64 format
        System.out.println("Generated Salt: " + Base64.getEncoder().encodeToString(salt));
    }
}