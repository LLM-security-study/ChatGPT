import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PRNG_1_Req24 {
    
    private static final int IV_SIZE = 16; // This is the recommended size for AES

    public static void main(String[] args) {
        try {
            SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
            
            // Generate a seed
            byte[] seed = new byte[IV_SIZE];
            rnd.nextBytes(seed);
            rnd.setSeed(seed);
            System.out.println("Seed: " + Base64.getEncoder().encodeToString(seed));
            
            // Generate an IV
            byte[] iv = new byte[IV_SIZE];
            rnd.nextBytes(iv);
            System.out.println("Initialization Vector: " + Base64.getEncoder().encodeToString(iv));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("PRNG not available: " + e.getMessage());
        }
    }
}