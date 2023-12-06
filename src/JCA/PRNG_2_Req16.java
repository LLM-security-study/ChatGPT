import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req16 {
    public static void main(String[] args) {

        // Instantiate SecureRandom
        SecureRandom sr = new SecureRandom();
        
        // Generate binary salt
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        
        // Convert to Base64 format
        String saltStr = Base64.getEncoder().encodeToString(salt);
        
        // Print the generated salt
        System.out.println("Generated salt for password hashing: " + saltStr);
    }
}