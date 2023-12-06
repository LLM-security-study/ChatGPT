import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req17 {

    public static void main(String[] args) {
        try {
            // secret key
            String secret = "secret";

            // message to be authenticated
            String message = "Hello, World!";

            // converting secret key to bytes
            byte[] secretByte = secret.getBytes(StandardCharsets.UTF_8);

            // creating a SecretKeySpec
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretByte, "HmacSHA256");

            // getting the instance of MAC
            Mac mac = Mac.getInstance("HmacSHA256");

            // initializing the MAC with secret key
            mac.init(secretKeySpec);

            // calculating MAC
            byte[] doFinal = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // encoding the MAC in base64 and printing it out
            System.out.println(Base64.getEncoder().encodeToString(doFinal));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}