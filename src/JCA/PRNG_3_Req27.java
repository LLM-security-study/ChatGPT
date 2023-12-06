import java.security.SecureRandom;

public class PRNG_3_Req27 {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();

        // initialize internal state
        byte[] bytes = new byte[20];
        sr.nextBytes(bytes);

        // generate a seed to initialize the internal state
        sr.setSeed(bytes);

        // generate a random integer intended for cryptographic applications
        int randomInt = sr.nextInt();
        
        System.out.println("Random Integer for Cryptographic Application : " + randomInt);
    }
}