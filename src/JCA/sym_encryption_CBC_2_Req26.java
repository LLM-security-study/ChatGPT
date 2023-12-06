import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req26 {

    // The Initialization Vector size for AES is 128 bits
    private static final int IV_SIZE = 16;

    public static void main(String[] args) {

        try {
            String secret = "My_Secret_Key_16"; // AES key (must be 16 bytes for AES-128)
            String plaintext = "This is the text to be encrypted";

            // Convert string secret key to SecretKeySpec
            SecretKey key = new SecretKeySpec(secret.getBytes(), "AES");

            // Generate a new random initialization vector
            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[IV_SIZE];
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Create a Cipher instance with AES in CBC mode with PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Initialize cipher
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            // Encrypt the plaintext
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

            // Concatenate IV and ciphertext and encode them in Base64
            byte[] output = new byte[IV_SIZE + ciphertext.length];
            System.arraycopy(iv, 0, output, 0, IV_SIZE);
            System.arraycopy(ciphertext, 0, output, IV_SIZE, ciphertext.length);
            String encodedOutput = Base64.getEncoder().encodeToString(output);

            System.out.println("Encrypted text: " + encodedOutput);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}