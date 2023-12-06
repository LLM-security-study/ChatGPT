import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req25 {
    public static void main(String[] args) {
        String message = "Hello, World!";
        String secret = "secretkey1";

        try{
            String macCode = calculateHMAC(message, secret);
            System.out.println("MAC code: " + macCode);
        } catch (Exception e) {
            System.out.println("Error occurred while generating MAC: " + e.getMessage());
        }
    }

    public static String calculateHMAC(String data, String key) throws Exception {
      SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(secret_key);
      byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
      Base64.Encoder encoder = Base64.getEncoder();
      return new String(encoder.encode(rawHmac));
   }
}