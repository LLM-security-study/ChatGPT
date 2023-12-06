import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req14 {
    public static void main(String[] args) {
        // Initialize SecureRandom
        SecureRandom random = new SecureRandom();

        // Generate a seed
        byte[] seed = random.generateSeed(20);
        random.setSeed(seed);

        // Generate Salt for Password Hashing
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Print Salt as Base64 for easy storage and readability
        String saltStr = Base64.getEncoder().encodeToString(salt);
        
        System.out.println("Generated Salt: " + saltStr);

    }
}