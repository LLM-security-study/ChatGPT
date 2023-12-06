import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req2 {
    public static void main(String[] args) {
        // Initialized SecureRandom
        SecureRandom secureRandom = new SecureRandom();
        // Create byte array for salt
        byte[] salt = new byte[16];
        // Get a random salt
        secureRandom.nextBytes(salt);
        // Print salt
        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
    }
}