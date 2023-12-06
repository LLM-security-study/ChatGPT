import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req22 {

    public static void main(String[] args) {
        
        SecureRandom prng = new SecureRandom();
        byte[] salt = new byte[16];

        prng.nextBytes(salt);
        
        String saltStr = Base64.getEncoder().encodeToString(salt);

        System.out.println("Generated Salt: " + saltStr);
    }
    
}