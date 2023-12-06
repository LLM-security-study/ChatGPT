import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req21 {
    public static void main(String[] args) {
        // Generate a seed for PRNG
        long seed = System.currentTimeMillis();
        // Create a SecureRandom object using the specified seed
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);
        
        // Generate a 16-byte IV for AES using PRNG
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Print the generated IV
        System.out.println("Generated Initialization Vector (IV): " + bytesToHex(iv));
    }
        
    // Function to convert byte array to hex string (used for printing the IV)
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}