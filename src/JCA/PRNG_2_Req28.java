import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req28 {
    public static void main(String[] args) {
        byte[] salt = createSalt();
        System.out.println(Base64.getEncoder().encodeToString(salt));
    }

    private static byte[] createSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] bytes = new byte[16];
        sr.nextBytes(bytes);
        return bytes;
    }
}