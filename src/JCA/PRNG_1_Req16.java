import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req16 {
    public static void main(String[] args) {
        try {
            // Create a secure random number generator
            SecureRandom secureRandom = new SecureRandom();

            // Get 128 random bytes
            byte[] randomBytes = new byte[16];
            secureRandom.nextBytes(randomBytes);

            // Create seed from random bytes
            long seed = 0;
            for (int i = 0; i < 8; i++) {
                seed |= ((long) randomBytes[i] & 0xffL) << (8 * i);
            }

            // Initialize a new random object with the seed
            SecureRandom prng = new SecureRandom();
            prng.setSeed(seed);

            // Generate IV for encryption
            byte[] iv = new byte[16];
            prng.nextBytes(iv);

            // Create IvParameterSpec object from the generated IV
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            System.out.println("Generated IV: " + new String(iv, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Error generating IV: " + e.getMessage());
        }
    }
}