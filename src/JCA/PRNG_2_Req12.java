import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req12
{
    public static void main(String[] args)
    {
        // Create SecureRandom instance
        SecureRandom prng = new SecureRandom();

        // Generate a long seed
        long seed = prng.nextLong();
        System.out.println("Generated Seed: " + seed);

        // Initialize the PRNG with the generated seed
        prng.setSeed(seed);

        // Generate a salt
        byte[] salt = new byte[16];
        prng.nextBytes(salt);

        // Encode salt to Base64 before printing
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        System.out.println("Generated Salt: " + encodedSalt);
    }
}