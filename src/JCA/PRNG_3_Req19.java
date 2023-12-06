import java.security.SecureRandom;

public class PRNG_3_Req19 {
    public static void main(String[] args) {
        
        // create instance of SecureRandom class
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate a random seed
        byte[] randomSeed = new byte[64];
        secureRandom.nextBytes(randomSeed);
        
        // Reinitialize the SecureRandom instance with the generated seed
        secureRandom.setSeed(randomSeed);
        
        // Generate a random integer
        int randomInteger = secureRandom.nextInt();
        
        System.out.println("The random integer generated is: " + randomInteger);
    }
}