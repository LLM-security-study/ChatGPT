import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class PRNG_1_Req20 {
    public static void main(String[] args) {
        try {
            // Generate a 128-bit seed
            SecureRandom secureRandom = new SecureRandom();
            byte[] seed = new byte[16];
            secureRandom.nextBytes(seed);
            
            // Print the seed
            System.out.println("Generated seed: " + Arrays.toString(seed));

            // Use the seed to generate IV
            SecureRandom secureRandomForIV = new SecureRandom(seed);
            byte[] iv = new byte[16]; // For AES, IV is typically 16 bytes
            secureRandomForIV.nextBytes(iv);

            // Create IV parameter spec
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            
            // Print the IV
            System.out.println("Initialization Vector of the Symmetric Key: " + Arrays.toString(iv));

        } catch (Exception ex) {
            System.out.println("Error generating seed or IV: " + ex.getMessage());
        }
    }
}