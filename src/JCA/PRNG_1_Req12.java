import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class PRNG_1_Req12 {
    private static SecureRandom prng;

    static {
        try {
            prng = SecureRandom.getInstance("SHA1PRNG");
            byte[] seed = new byte[16];
            prng.nextBytes(seed);
            prng.setSeed(seed);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static IvParameterSpec generateIV() {
        byte[] ivBytes = new byte[16];
        prng.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

    public static void main(String[] args) {
        IvParameterSpec iv = generateIV();
        byte[] ivBytes = iv.getIV();
        System.out.println("Generated IV: " + Base64.getEncoder().encodeToString(ivBytes));
    }
}