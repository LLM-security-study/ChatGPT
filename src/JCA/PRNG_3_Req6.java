import java.security.SecureRandom;

public class PRNG_3_Req6 {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        
        // generate seed and set it to the secureRandom object
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        
        // reseed the secureRandom object
        secureRandom.setSeed(bytes);
        
        // generate random integer
        int randomInt = secureRandom.nextInt();
        
        System.out.println("Generated Random Integer: " + randomInt);
    }
}