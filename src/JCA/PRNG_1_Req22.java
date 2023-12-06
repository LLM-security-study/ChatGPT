import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req22 {
    private static final int IV_SIZE = 16;  // 128 bits

    public static void main(String[] args) {
        // Create a secure random number generator
        SecureRandom secureRandom = new SecureRandom();
        
        // Get 8 bytes for the seed
        byte[] seed = new byte[8];
        secureRandom.nextBytes(seed);
        
        // Now reinitialize the SecureRandom with the given seed.
        secureRandom = new SecureRandom(seed);
        
        // Use the PRNG to generate IV
        byte[] iv = new byte[IV_SIZE];
        secureRandom.nextBytes(iv);

        // Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Print seed and Initialization Vector
        System.out.println("Seed: " + bytesToHex(seed));
        System.out.println("Initialization Vector: " + bytesToHex(iv));
    }
    
    // Utility function to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}