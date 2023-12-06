import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req27 {
    public static void main(String[] args) {
        try {
            // Create a secure random number generator
            SecureRandom secureRandom = new SecureRandom();

            // Get 128 random bits for seed
            byte[] seed = new byte[16];
            secureRandom.nextBytes(seed);

            // Reset the secure random number generator with the given seed.
            secureRandom.setSeed(seed);

            // Use the generator to generate an initialization vector (IV)
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            System.out.println("Seed: " + bytesToHex(seed));
            System.out.println("IV: " + bytesToHex(iv));
        } catch (Exception ex) {
            System.out.println("Error generating IV: " + ex.getMessage());
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}