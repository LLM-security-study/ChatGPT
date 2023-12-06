import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req15 {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        System.out.println("Generated salt: " + encodedSalt);
    }
}