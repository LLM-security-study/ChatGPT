import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req6 {

    public static void main(String[] args) {
        // Use SecureRandom to generate seed for PRNG
        SecureRandom sr = new SecureRandom();
        byte[] seed = new byte[16];
        sr.nextBytes(seed);

        // Use the seed to initialize a new instance of SecureRandom
        SecureRandom prng = new SecureRandom(seed);
        
        // Use the PRNG to generate an IV
        byte[] iv = new byte[16]; // Default length for an IV is usually 16 bytes
        prng.nextBytes(iv);

        // Print the seed and the iv
        System.out.println("Seed: " + bytesToHex(seed));
        System.out.println("IV: " + bytesToHex(iv));
    }

    // Helper method to convert byte array to hex String
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}