import java.security.SecureRandom;
import java.math.BigInteger;

public class PRNG_3_Req26 {

    public static void main(String[] args) {

        // Instantiate SecureRandom
        SecureRandom secureRandom = new SecureRandom();

        // Generate a random seed
        byte[] seed = new byte[16];     // 128 bits
        secureRandom.nextBytes(seed);

        // Re-seed secureRandom
        secureRandom.setSeed(seed);

        // Use the re-seeded SecureRandom to generate random numbers
        BigInteger bigInteger = new BigInteger(130, secureRandom);   // 130-bit random BigInteger

        // Print the random seed
        System.out.println("Seed: " + new BigInteger(1, seed).toString(16));

        // Print the random BigInteger
        System.out.println("Random BigInteger: " + bigInteger.toString(16));

        // Create another secureRandom instance for XOR
        SecureRandom xorSecureRandom = new SecureRandom();

        // Calculate the XOR of bigInteger with a newly generated 130-bit random BigInteger
        BigInteger xorBigInteger = bigInteger.xor(new BigInteger(130, xorSecureRandom));

        // Print the XOR BigInteger
        System.out.println("XOR BigInteger: " + xorBigInteger.toString(16));
    }
}