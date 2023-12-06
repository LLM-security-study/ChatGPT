import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req18 {
    public static void main(String[] args) {
        try {
            // Create a secure random number generator
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            // Create seed for PRNG
            long secureInitializer = secureRandom.nextLong();
            secureRandom.setSeed(secureInitializer);

            // Create Initialization Vector
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec initializationVector = new IvParameterSpec(iv);

            // For testing print the IV
            for (byte b : iv) {
                System.out.print(b);
            }
            System.out.println();

        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
    }
}