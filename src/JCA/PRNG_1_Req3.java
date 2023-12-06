import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req3 {
    static final int IV_SIZE = 16; // 128 bits

    public static void main(String[] args) {
        // Create SecureRandom instance
        SecureRandom random = new SecureRandom();
        
        // Create seed bytes
        byte[] seed = new byte[20];
        random.nextBytes(seed);
        
        // Now, let's use the seed to generate pseudorandom numbers
        SecureRandom prng = new SecureRandom(seed);
        
        // Generate IV for encryption 
        byte[] iv = new byte[IV_SIZE];
        prng.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // print generated IV
        System.out.println("Generated IV: " + bytesToHex(iv));
    }
    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}