import java.security.SecureRandom;

public class PRNG_3_Req18 {
    public static void main(String[] args) {
        // Create a SecureRandom instance
        SecureRandom secureRandom = new SecureRandom();

        // Generate random bytes to use as seed
        byte[] seed = new byte[10];
        secureRandom.nextBytes(seed);

        // Set seed for random number generation
        secureRandom.setSeed(seed);
        
        // Generate a secure random integer
        int randomInt = secureRandom.nextInt();

        System.out.println("Seed: " + new String(seed));
        System.out.println("Secure Random Integer: " + randomInt);
    }
}