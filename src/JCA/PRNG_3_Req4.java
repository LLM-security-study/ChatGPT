import java.security.SecureRandom;
import java.math.BigInteger;

public class PRNG_3_Req4 {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);

        secureRandom.setSeed(randomBytes);

        BigInteger bigInteger = new BigInteger(130, secureRandom);
        System.out.println("Random BigInteger: " + bigInteger);
    }
}