import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req6 {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        System.out.println("Generated Salt: " + saltStr);
    }
}