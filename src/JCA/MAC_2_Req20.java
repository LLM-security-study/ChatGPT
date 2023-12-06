import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req20 {
    private static String secretKey = "secret-key";

    public static void main(String[] args) {
        try {
            String msg = "Hello World!";
            String macResult = calculateHMAC(msg, secretKey);
            System.out.println("HMAC: " + macResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateHMAC(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);  // Convert byte[] to string 
    }
}