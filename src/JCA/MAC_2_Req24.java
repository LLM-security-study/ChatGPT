import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC_2_Req24 {
    public static void main(String[] args) {
        String message = "Test message";
        String secret = "secret";

        try {
            // Create a Mac instance with the specified algorithm name.
            Mac mac = Mac.getInstance("HmacSHA256");

            // Create a SecretKeySpec object from the secret string.
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // Initialise the Mac instance with the secret key.
            mac.init(secretKey);

            // Compute the Mac function on the input message.
            byte[] rawMac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Base64-encode the result to get a string that can be safely stored and transmitted.
            String macString = Base64.getEncoder().encodeToString(rawMac);

            System.out.println("MAC: " + macString);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}