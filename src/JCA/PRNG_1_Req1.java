import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req1 {

  public static void main(String[] args) {
    try {
      // Generate a pseudorandom seed
      SecureRandom secureRandom = new SecureRandom();
      byte[] seed = new byte[8];
      secureRandom.nextBytes(seed);

      System.out.println("Generated Seed: " + Arrays.toString(seed));
    
      // Use this seed to generate a pseudorandom number generator
      SecureRandom prng = new SecureRandom(seed);
    
      // Generate an Initialization Vector (IV) for encryption
      byte[] ivBytes = new byte[16];
      prng.nextBytes(ivBytes);
      IvParameterSpec iv = new IvParameterSpec(ivBytes);

      System.out.println("Generated IV: " + Arrays.toString(iv.getIV()));

    } catch (Exception e) {
      System.out.println("Exception: " + e.toString());
    }
  }
}