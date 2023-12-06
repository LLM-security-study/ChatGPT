import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req25 {
    public static void main(String[] args) {
        try {
            // Create secure random number generator
            SecureRandom sr = new SecureRandom();
            
            // Create array for seed
            byte[] seed = new byte[20];
            // Get random seed
            sr.nextBytes(seed);
            
            System.out.println("Generated Seed: " + Base64.getEncoder().encodeToString(seed));

            // Initialize random number generator with seed
            SecureRandom prng = new SecureRandom(seed);

            // Create array for IV
            byte[] iv = new byte[16]; // IV is typically 16 bytes for AES
            // Get random IV
            prng.nextBytes(iv);
            
            // Create IV spec
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            System.out.println("Generated IV: " + Base64.getEncoder().encodeToString(iv));
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
}