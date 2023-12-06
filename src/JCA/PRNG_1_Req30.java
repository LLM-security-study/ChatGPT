import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;

public class PRNG_1_Req30 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Random number generation
        SecureRandom secureRandom = new SecureRandom();
        byte[] values = new byte[20];
        secureRandom.nextBytes(values);
        
        // Seed generation
        byte[] seed = secureRandom.generateSeed(8);
        System.out.println("Generated Seed: " + Arrays.toString(seed));

        // PRNG Initialization
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        prng.setSeed(seed);

        // IV generation
        byte[] iv = new byte[16];
        prng.nextBytes(iv);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        System.out.println("Generated IV: " + Arrays.toString(ivSpec.getIV()));
    }
}