import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req3 {
    public static void main(String[] args) {
        try {
            // The message 
            String message = "This is a confidential message";

            // The shared secret
            String secret = "secret-key";

            System.out.println("Original Message: " + message);
            String macValue = computeMac(message, secret);
            System.out.println("MAC Value: " + macValue);

        } catch (Exception e) {
            System.out.println("Error occurred while computing HMAC: " + e);
        }
    }

    private static String computeMac(String message, String secret) throws Exception {
        // Get an algorithm instance
        Mac mac = Mac.getInstance("HmacSHA256");

        // Create secret key spec
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        // Initialize the MAC object
        mac.init(secretKeySpec);

        // Compute the MAC value
        byte[] macValue = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // Convert to base64 string for better readability
        return Base64.getEncoder().encodeToString(macValue);
    }
}