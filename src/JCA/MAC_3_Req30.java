import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req30 {
    public static void main(String[] args) {
        try {
            String secretKey = "theSecretKey";
            String message = "Hello, World!";

            // Hash-based MAC generation
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");

            byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");

            hmacSha256.init(secretKeySpec);

            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] macBytes  = hmacSha256.doFinal(messageBytes);

            // Convert MAC to Base64
            String macBase64 = Base64.getEncoder().encodeToString(macBytes);

            System.out.println("MAC for message \"" + message + "\" is: " + macBase64);
        } catch (Exception e) {
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }
}