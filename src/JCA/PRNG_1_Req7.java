import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_1_Req7 {
    
    private static final int IV_SIZE = 16; // 128-bit IV for AES

    public static void main(String[] args) {
        // Create a secure random number generator
        SecureRandom sr = new SecureRandom();

        // Get 16 random bytes
        byte[] iv = new byte[IV_SIZE];
        sr.nextBytes(iv);

        // Convert byte array to a Base64 string
        String ivBase64 = Base64.getEncoder().encodeToString(iv);

        System.out.println("Random IV generated: " + ivBase64);
    }
}