import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC_2_Req10 {
  public static void main(String[] args) {
    String message = "This is a test message";
    String secret = "secret-key";

    String hash = calculateHMAC(message, secret);
    System.out.println("The HMAC for the message is: " + hash);
  }

  public static String calculateHMAC(String data, String key) {
    String result = "";
    try {
      SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(secretKey);
      byte[] hmacData = mac.doFinal(data.getBytes("UTF-8"));
      result = Base64.getEncoder().encodeToString(hmacData);
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate hmac", e);
    }
    return result;
  }
}