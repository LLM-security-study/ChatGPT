import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class MAC_3_Req22 {
    private static final String SECRET_KEY = "secretKey";

    public static void main(String[] args) {
        String message = "Hello, World!";
        String mac = calculateHMAC(message, SECRET_KEY);
        System.out.println("MAC: " + mac);
    }

    public static String calculateHMAC(String data, String key) {
        String result = null;
        try {
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secret_key);
            byte[] rawMac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(rawMac);
        } catch (Exception e) {
            System.out.println("Error calculating HMAC: " + e.getMessage());
        }
        return result;
    }
}