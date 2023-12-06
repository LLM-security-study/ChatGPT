import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req24 {
    
    public static void main(String[] args) {
        SecureRandom prng = new SecureRandom();
        
        byte[] randomBytes = new byte[64];
        prng.nextBytes(randomBytes);
        
        String seed = Base64.getEncoder().encodeToString(randomBytes);
        System.out.println("PRNG Seed: " + seed);

        byte[] salt = new byte[16];
        prng.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        System.out.println("Generated Salt: " + encodedSalt);
    }
}