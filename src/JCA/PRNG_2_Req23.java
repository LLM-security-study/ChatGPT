import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PRNG_2_Req23 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 1. Generating a seed for PRNG
        SecureRandom secureRandomForSeed = new SecureRandom();
        byte[] seed = secureRandomForSeed.generateSeed(20); // 20 bytes seed

        // 2. Initialize PRNG with seed
        SecureRandom secureRandom = new SecureRandom(seed);

        // 3. Generate salt
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        
        // 4. Convert salt to string for storage and use in hashing process
        String saltStr = Base64.getEncoder().encodeToString(salt);
        System.out.println("Generated salt: " + saltStr);
    }
}