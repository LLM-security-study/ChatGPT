import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req17 {

    public static void main(String[] args) {
        // Generate Seed for PRNG
        SecureRandom seedGenerator = new SecureRandom();
        byte[] seed = seedGenerator.generateSeed(20); // 20-byte (160-bit) seed

        // Initialize PRNG with the generated Seed
        SecureRandom prng = new SecureRandom(seed);

        // Generate Salt for password hashing
        byte[] salt = new byte[16]; // 16-byte (128-bit) salt 
        prng.nextBytes(salt);

        // print the generated salt
        String saltStr = Base64.getEncoder().encodeToString(salt);
        System.out.println("The generated salt is: " + saltStr);

        // Note: for actual password hashing, use the salt byte array
    }

}