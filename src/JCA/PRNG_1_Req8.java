import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req8 {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        byte[] seed = new byte[20];
        sr.nextBytes(seed); // Generate a seed for PRNG

        SecureRandom prng = new SecureRandom(seed); // Using seed to create PRNG

        byte[] iv = new byte[16]; // This is the standard size for AES block
        prng.nextBytes(iv); // Generate an IV

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        System.out.println("Seed for PRNG: " + bytesToHex(seed));
        System.out.println("Generated IV: " + bytesToHex(iv));
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}