// Import required packages
import java.security.SecureRandom;

public class PRNG_3_Req2 {
    public static void main(String[] args){
        // Create a SecureRandom instance
        SecureRandom random = new SecureRandom();
    
        // Generate seed bytes
        byte[] seed = new byte[20];
        random.nextBytes(seed);
    
        // Use the seed to initialize SecureRandom instance
        random.setSeed(seed);
    
        // Generate a random integer
        int secureInt = random.nextInt();
    
        // Output the generated random integer
        System.out.println("Secure random integer: " + secureInt);
    }
}