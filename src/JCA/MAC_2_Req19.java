import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MAC_2_Req19 {
    public static void main(String[] args) {
        try {
            // The message
            String message = "This is a test message";

            // The secret key
            byte[] keyBytes = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);

            // Get an HMAC-MD5 key from the raw key bytes
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacMD5");

            // Get an instance of the MAC mechanism
            Mac mac = Mac.getInstance("HmacMD5");

            // Initialize the MAC mechanism
            mac.init(secretKey);

            // Compute the MAC
            byte[] resultBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Print the result
            System.out.println(Arrays.toString(resultBytes));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}