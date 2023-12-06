// necessary imports
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

public class PRNG_1_Req9 {

    public static void main(String[] args) {
        
        // create a SecureRandom instance
        SecureRandom sr = new SecureRandom();

        // Generate a seed for the PRNG
        byte[] seed = new byte[16]; // change the size as needed 
        sr.nextBytes(seed);

        System.out.println("Generated PRNG Seed: " + DatatypeConverter.printHexBinary(seed));
        
        // Now, use this PRNG to generate an Initialization Vector for encryption.
        byte[] iv = new byte[16]; // our IV size, change as needed
        sr.nextBytes(iv);
        
        System.out.println("Generated IV: " + DatatypeConverter.printHexBinary(iv));
    }
}