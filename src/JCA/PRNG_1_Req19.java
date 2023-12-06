import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req19 {
    public static void main(String[] args) {
        try {
            // Generate a random seed for PRNG
            SecureRandom rd = new SecureRandom();
            byte[] seed = new byte[20];
            rd.nextBytes(seed);
            
            // Initialize the PRNG with the seed
            SecureRandom prng = new SecureRandom(seed);
            
            // Generate an IV for encryption
            byte[] ivBytes = new byte[16]; // for AES, for example
            prng.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            
            System.out.println("Generated seed (base64): " + Base64.getEncoder().encodeToString(seed));
            System.out.println("Generated IV (base64): " + Base64.getEncoder().encodeToString(ivBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}