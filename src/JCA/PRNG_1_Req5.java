import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req5 {
    private static final int IV_SIZE = 16;  // IV size in bytes

    public static void main(String[] args) {
        try {
            // Initialize a pseudo random number generator (PRNG)
            SecureRandom secureRandom = new SecureRandom();
            byte[] seed = secureRandom.generateSeed(20);

            // Use the PRNG to generate an Initialization Vector (IV)
            SecureRandom prng = new SecureRandom(seed);
            byte[] iv = new byte[IV_SIZE];
            prng.nextBytes(iv);
            
            // Create an Initialization Vector Specification
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            System.out.println("Seed generated: " + bytesToHex(seed));
            System.out.println("Initialization Vector (IV): " + bytesToHex(iv));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}