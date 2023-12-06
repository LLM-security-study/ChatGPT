import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req1 {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        // Generate a seed
        byte[] seed = new byte[8];
        secureRandom.nextBytes(seed);
        secureRandom.setSeed(seed);

        // Generate a salt
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        // Convert salt bytes into a string with Base64
        String saltString = Base64.getEncoder().encodeToString(salt);

        System.out.println("Seed for PRNG: " + Base64.getEncoder().encodeToString(seed));
        System.out.println("Generated Salt: " + saltString);
    }

}