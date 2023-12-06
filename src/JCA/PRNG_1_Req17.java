import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req17 {
    
    public static void main(String[] args) {
        try {
            // create a secure random number generator
            SecureRandom secRandom = SecureRandom.getInstance("SHA1PRNG");
    
            // generate a seed for initializing PRNG
            byte[] seed = new byte[16];
            secRandom.nextBytes(seed);
            
            // print the seed
            System.out.println("Seed: " + bytesToHex(seed));
            
            // reinitialize the PRNG with the generated seed
            secRandom = SecureRandom.getInstance("SHA1PRNG");
            secRandom.setSeed(seed);
            
            // use the PRNG to generate an Initialization Vector (IV) for encryption
            byte[] iv = new byte[16]; // using 16 bytes (or 128 bit) for AES
            secRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            // print the IV
            System.out.println("IV: " + bytesToHex(iv));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to convert a byte array to a hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}