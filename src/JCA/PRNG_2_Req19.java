import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req19 {
  public static void main(String[] args) {

    // Generate a seed
    SecureRandom secureRandomSeed = new SecureRandom();
    byte[] seed = new byte[20];
    secureRandomSeed.nextBytes(seed);

    // Initialize a Pseudo Random Number Generator with the seed
    SecureRandom prng = new SecureRandom(seed);

    // Generate a 16 bytes salt
    byte[] salt = new byte[16];
    prng.nextBytes(salt);

    // Convert the salt to a base64 string
    String saltBase64 = Base64.getEncoder().encodeToString(salt);

    // Print the random seed and the generated salt
    System.out.println("Random Seed: " + Base64.getEncoder().encodeToString(seed));
    System.out.println("Generated Salt: " + saltBase64);
  }
}