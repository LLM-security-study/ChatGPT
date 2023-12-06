import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req28 {
    public static void main(String[] args) throws Exception {
        String secretKey = "your-secret-key"; // replace with your secret key
        String message = "Hello, World!"; // replace with your message
        
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKeySpec);

        String hash = Base64.getEncoder().encodeToString(sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        System.out.println("MAC: " + hash);
    }
}