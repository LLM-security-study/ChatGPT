import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req8 {

    public static void main(String[] args) {

        // Generate a seed for the PRNG
        SecureRandom secureSeedRandom = new SecureRandom();
        byte[] seed = new byte[16];
        secureSeedRandom.nextBytes(seed);

        // Use the seed to initialize another SecureRandom instance
        SecureRandom secureRandom = new SecureRandom(seed);

        // Create byte array for the salt
        byte[] salt = new byte[16];

        // Generate a random salt
        secureRandom.nextBytes(salt);

        // Base64 encode the salt
        String saltStr = Base64.getEncoder().encodeToString(salt);

        System.out.println("Generated Salt: " + saltStr);
    }
}