import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req28 {
    public static void main(String[] args) throws Exception {
        // The secret key used for the MAC
        String secretKey = "SomeSecretKey";

        // The message to be MACed
        String message = "Hello World";

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);

        byte[] macData = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("MAC: " + Base64.getEncoder().encodeToString(macData));
    }
}