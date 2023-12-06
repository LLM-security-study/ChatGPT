import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MAC_3_Req27 {

    public static void main(String[] args) {
        String key = "secretkey";
        String message = "Hello, World!";

        try {
            // create a key object from a given key string
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // get an instance of Mac object
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            // calculate mac
            byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // print mac
            System.out.println(Arrays.toString(macBytes));

        } catch (Exception e) {
            System.out.println("Error while calculating MAC: " + e.getMessage());
        }
    }
}