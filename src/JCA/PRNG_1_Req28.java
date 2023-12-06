import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req28 {
  
  public static void main(String[] args) throws Exception {
        // Generate a seed for PRNG
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);

        // Initialize PRNG with the generated seed
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        prng.setSeed(seed);

        // Use the PRNG to generate an IV
        byte[] iv = new byte[128/8];
        prng.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Print the generated IV
        System.out.println("Generated Initialization Vector: ");
        for(byte b : ivSpec.getIV()) {
            System.out.format("%02x", b);
        }
    }
}