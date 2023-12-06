import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_2_Req12 {

    public static final String AES_KEY = "0123456789012345"; // 16 bytes secret key
    public static final String IV_STRING = "6789012345012345"; // 16 bytes IV string

    public static void main(String[] args) {
        String text = "Hello, World!";

        byte[] cipherText = encrypt(text);

        System.out.println("Original Text: " + text);
        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(cipherText));
    }

    public static byte[] encrypt(String textToEncrypt) {
        try {
            // Create a SecretKeySpec object
            SecretKey secretKey = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            // Create an IvParameterSpec object
            IvParameterSpec ivSpec = new IvParameterSpec(IV_STRING.getBytes(StandardCharsets.UTF_8));

            // Get a Cipher instance
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Initialize the Cipher
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            // Encrypt the data
            return cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed.", e);
        }
    }
}