import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
  
public class MAC_2_Req17 {
    public static void main(String[] args) throws Exception {
        // Generate secret key for HMAC-SHA256
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();
  
        // Get instance of Mac object implementing HMAC-SHA256, and initialize it with the above secret key
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
  
        String message = "Hello, World";

        // Compute the hmac on input data bytes
        byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
  
        // bytes[] is the MAC result
        System.out.println(Base64.getEncoder().encodeToString(bytes));
    }
}