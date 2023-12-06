import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req10 {
    public static void main(String[] args) throws Exception {
        // Generate seed for PRNG
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = new byte[8];
        secureRandom.setSeed(seed);

        // Use this PRNG for generating an Initialization Vector (IV)
        byte[] iv = new byte[Cipher.getMaxAllowedKeyLength("AES")/8];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Print the IV
        System.out.print("Initialization Vector: ");
        for (byte b : iv) {
            System.out.printf("%02x", b);
        }
    }
}