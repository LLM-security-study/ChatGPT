// Required import statements
import java.security.SecureRandom;
import java.util.Base64;

public class PRNG_2_Req27 {
    // Define a constant for the salt byte size (16 in this case)
    private static final int SALT_BYTE_SIZE = 16;

    public static void main(String[] args) {
        // Generate seed using PRNG
        SecureRandom secureRandom = new SecureRandom();
        // Generate salt
        byte[] salt = new byte[SALT_BYTE_SIZE];
        secureRandom.nextBytes(salt);
        // Convert salt to base64 string
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        // Print the generated salt
        System.out.println("Generated Salt: " + encodedSalt);
    }
}