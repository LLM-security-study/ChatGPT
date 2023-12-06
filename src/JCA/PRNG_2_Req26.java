import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req26 {

    public static void main(String[] args) {
        // Create a secure random number generator using the "SHA1PRNG" algorithm
        SecureRandom sr = new SecureRandom();
        
        // Create a seed for the random number generator
        sr.setSeed(sr.generateSeed(20));
        
        // Generate a salt for password hashing
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        
        // Convert the salt to a string and print it
        String saltStr = Base64.getEncoder().encodeToString(salt);
        System.out.println("The generated salt is: " + saltStr);
    }
}