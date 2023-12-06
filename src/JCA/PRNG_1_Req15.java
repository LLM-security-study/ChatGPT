import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PRNG_1_Req15 {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    // Generate a seed for initializing a PRNG
    SecureRandom seeder = new SecureRandom();
    byte[] seed = new byte[20];
    seeder.nextBytes(seed);
    
    // Initialize PRNG with the generated seed.
    SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
    prng.setSeed(seed);
    
    // Generate IV for encryption of size 16 Bytes (commonly used size for AES)
    byte[] iv = new byte[16];
    prng.nextBytes(iv);
    
    // Print IV in hexadecimal form
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < iv.length; i++) {
      hexString.append(Integer.toHexString(0xFF & iv[i]));
    }
    System.out.println("Generated IV: " + hexString.toString());
  }
}