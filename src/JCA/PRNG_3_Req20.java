import java.security.SecureRandom;
import java.math.BigInteger;

public class PRNG_3_Req20 {
    public static void main(String[] args) {
        // Create a secure random number generator
        SecureRandom secRandom = new SecureRandom();

        // Get 128 random bits
        byte[] seed = secRandom.generateSeed(128);

        // Create another random number generator using the first 16 bytes
        SecureRandom rand = new SecureRandom(seed);

        // Generate a random BigInteger from [0, 2^16)
        BigInteger randomInt = new BigInteger(16, rand);

        // Print the random integer
        System.out.println("Random integer: " + randomInt);
    }
}