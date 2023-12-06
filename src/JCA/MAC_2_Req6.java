import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MAC_2_Req6 {
    public static void main(String[] args) {
        try {
            String secretKey = "secretkey";
            String message = "Hello, World!";

            byte[] macResult = computeMAC(secretKey, message);

            System.out.println(Arrays.toString(macResult));
        } catch (Exception ex) {
            System.out.println("Error occurred while computing MAC: " + ex.getMessage());
        }
    }

    private static byte[] computeMAC(String secretKey, String message) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);

        return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }
}