import java.security.SecureRandom;

public class PRNG_3_Req14 {
  public static void main(String[] args) {
    SecureRandom secureRandom = new SecureRandom();

    // Get the seed
    byte[] seed = secureRandom.generateSeed(20);

    // Now use this seed to generate a secure random number
    secureRandom.setSeed(seed);

    // Generate a random integer
    int randomInteger = secureRandom.nextInt();
    System.out.println("The random integer is: " + randomInteger);
  }
}