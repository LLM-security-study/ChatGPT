import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req9 {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] values = new byte[20];
        secureRandom.nextBytes(values);

        // Generate seed for PRNG
        long seed = secureRandom.generateSeed(8).hashCode();
        System.out.println("Seed for PRNG: " + seed);
        
        // Use the seed to generate salt for password hashing
        SecureRandom prng = new SecureRandom();
        prng.setSeed(seed);
        byte[] salt = new byte[16];
        prng.nextBytes(salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        System.out.println("Generated salt for password hashing: " + saltString);
    }
}