import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req4 {
    public static void main(String[] args) {
        try {
            // Generate a seed for PRNG
            SecureRandom secureRandom = new SecureRandom();
            byte[] seed = new byte[20];
            secureRandom.nextBytes(seed);

            // Initialize PRNG with the generated seed
            SecureRandom prng = new SecureRandom(seed);

            // Generate an Initialization Vector (IV) for encryption
            byte[] iv = new byte[16]; // as AES uses a block size of 16 bytes
            prng.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Print the generated IV
            System.out.println("Generated Initialization Vector (IV): ");
            for (byte b : ivParameterSpec.getIV()) {
                System.out.format("%02x", b);
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}