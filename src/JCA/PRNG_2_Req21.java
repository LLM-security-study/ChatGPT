import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req21 {
    public static void main(String[] args) {
        // Initialize PRNG and create seed
        SecureRandom secureRandom = new SecureRandom();
        byte[] values = new byte[20];
        secureRandom.nextBytes(values);

        // Generate salt for password hashing
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        // Convert salt to string for storage
        String saltStr = Base64.getEncoder().encodeToString(salt);

        // Print generated salt
        System.out.println("PRNG Seed: " + Base64.getEncoder().encodeToString(values));
        System.out.println("Generated Salt: " + saltStr);
    }
}