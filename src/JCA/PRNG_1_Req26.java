import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_1_Req26 {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        
        // generate the seed
        byte[] seed = new byte[20];
        random.nextBytes(seed);
        System.out.println("Generated Seed: " + Base64.getEncoder().encodeToString(seed));

        random.setSeed(seed);

        // generate the IV
        byte[] iv = new byte[16]; // we're assuming AES therefore the IV should be 16 bytes
        random.nextBytes(iv);
        System.out.println("Generated IV: " + Base64.getEncoder().encodeToString(iv));
    }
}