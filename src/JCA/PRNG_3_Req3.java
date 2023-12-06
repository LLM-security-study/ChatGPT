import java.security.SecureRandom;

public class PRNG_3_Req3 {
    public static void main(String[] args) {
        // SecureRandom instance with default algorithm and provider
        SecureRandom secureRandom = new SecureRandom();
        
        // Get random integer
        int randomInt = secureRandom.nextInt();

        // Print the generated integer
        System.out.println("Generated random integer for cryptography: " + randomInt);
    }
}