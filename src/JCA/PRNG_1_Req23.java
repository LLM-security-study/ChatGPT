import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req23 {
    public static void main(String [] args){
        
        // Initialize SecureRandom
        SecureRandom sr = new SecureRandom();

        // Generate seed bytes 
        byte[] seed = new byte[20];
        sr.nextBytes(seed);

        // Set the new seed
        sr.setSeed(seed);
        
        // Use SecureRandom to generate IV
        byte[] iv = new byte[16]; // Assuming block size is 16 bytes
        sr.nextBytes(iv);
        
        // Create IvParameterSpec object
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        
        System.out.println("Seed: " + Arrays.toString(seed));
        System.out.println("IV: " + Arrays.toString(iv));
    }
}