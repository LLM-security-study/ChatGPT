import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req5 {
    public static void main(String[] args) {
        // Generate a seed
        long seed = System.nanoTime();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);
        
        // Generate salt
        byte[] salt = new byte[16];  // You may choose the length of salt according to your requirements
        secureRandom.nextBytes(salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        
        System.out.println("Generated salt: "+saltStr);
    }
}