import java.security.SecureRandom;

public class PRNG_3_Req15 {
    public static void main(String[] args) {
        // Create secure random seed
        SecureRandom secureRandom = new SecureRandom();

        // Generate random bytes
        byte[] randomBytes = new byte[20];
        secureRandom.nextBytes(randomBytes);

        // Create new SecureRandom with the generated seed
        SecureRandom randomGeneratorWithSeed = new SecureRandom(randomBytes);

        // Generate random integer
        int randomInt = randomGeneratorWithSeed.nextInt();
       
        System.out.println("Generated secure random integer: " + randomInt);
    }
}