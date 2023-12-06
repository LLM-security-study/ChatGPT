import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req12 {
    public static void main(String[] args) {
        String message = "Hello World";
        String secret = "secretkey";

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKey);

            String hash = Base64.getEncoder().encodeToString(sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
            System.out.println("MAC: " + hash);
        } catch (Exception e) {
            System.out.println("Error while calculating MAC: " + e.getMessage());
            e.printStackTrace();
        }
    }
}