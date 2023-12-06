import java.security.SecureRandom;

public class PRNG_3_Req22 {
    public static void main(String[] args) {
        // SecureRandom instance provides a cryptographically strong random number generator
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate a 20-byte seed
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);
        
        // Initialize the cipher with the generated seed
        SecureRandom secureRandomForCipher = new SecureRandom(seed);
        
        // Generate a random integer
        int randomInteger = secureRandomForCipher.nextInt();
        System.out.println("Random Integer for Cryptographic Applications: " + randomInteger);
    }
}