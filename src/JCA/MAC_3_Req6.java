import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MAC_3_Req6 {
  public static void main(String[] args) {
      String secret = "secret-key";
      String message = "Hello, World!";
      try {
          String hash = calculateHMAC(secret, message);
          System.out.println(hash);
      } catch (Exception e) {
          System.out.println("Failed to calculate HMAC: " + e.getMessage());
      }
  }

  private static String calculateHMAC(String secret, String message) throws Exception {
      SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(secretKeySpec);
      byte[] hmacBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hmacBytes);
  }
}
