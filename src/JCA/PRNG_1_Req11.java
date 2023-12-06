import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req11 {

    private static final int IV_SIZE = 16;  // 16 bytes = 128 bits (AES block size)

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        // Generate a seed for PRNG
        byte[] seed = new byte[64];  // 64 bytes = 512 bits
        secureRandom.nextBytes(seed);

        // Now, use this seed to create a new instance of SecureRandom
        SecureRandom prng = new SecureRandom(seed);

        // Generate an Initialization Vector (IV) using this PRNG
        byte[] iv = new byte[IV_SIZE];
        prng.nextBytes(iv);

        // Use the iv for creating IvParameterSpec object which can be used for encryption
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // The ivSpec can now be used for performing AES encryption
    }
}