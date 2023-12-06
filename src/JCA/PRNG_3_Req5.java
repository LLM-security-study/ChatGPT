import java.security.SecureRandom;

public class PRNG_3_Req5 {
    public static void main(String[] args) {
                // You can use the no-argument constructor to create a SecureRandom object
                SecureRandom secureRandom = new SecureRandom();

                 // Generate a seed for the secure random number generator.
                byte[] seed = new byte[20];
                secureRandom.nextBytes(seed);

                // Alternatively, you may regenerate the same secure random number by providing the seed.
                SecureRandom secureRandomWithSeed = new SecureRandom(seed);

                // Then, you can use the SecureRandom object to generate random integers.
                int randomInt = secureRandomWithSeed.nextInt();
                System.out.println("Generated Secure Random Integer: " + randomInt);
    }
}