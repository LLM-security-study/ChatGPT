import java.security.SecureRandom;

public class PRNG_3_Req9 {
    public static void main(String[] args) {
        // Creates a secure random number generator
        SecureRandom secureRandom = new SecureRandom();

        // Get 16 random bytes for seed
        byte[] seed = new byte[16];
        secureRandom.nextBytes(seed);

        // Use the seed to initialize another secure random number generator
        SecureRandom secureRandomForGen = new SecureRandom(seed);

        // Generate an integer
        int randomInt = secureRandomForGen.nextInt();

        System.out.println("Random Generated Integer : " + randomInt);
    }
}