import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req22 {
    public static void main(String[] args) {
        String message = "Hello, World!";
        String secret = "secretkey";

        try {
            // Get an instance of the HMACSHA256 Mac
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");

            // Create a new secret key
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKey);

            // Compute the hmac on input data bytes
            byte[] hmacSha256Bytes = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert hmacSha256Bytes to Base64 format
            String checksum = Base64.getEncoder().encodeToString(hmacSha256Bytes);
            
            System.out.println("Message: " + message);
            System.out.println("Message Checksum: " + checksum);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}