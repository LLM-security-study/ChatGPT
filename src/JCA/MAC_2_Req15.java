import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req15 {

    public static void main(String[] args) throws Exception {
        String secretKey = "MySecretKey";
        String message = "This is a message that needs to be authenticated.";

        // Generate the HMAC-SHA256 MAC for the message
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] macBytes = mac.doFinal(message.getBytes());

        // Convert to base64 for easier viewing
        String macBase64 = Base64.getEncoder().encodeToString(macBytes);

        System.out.println("MAC: " + macBase64);
    }
}