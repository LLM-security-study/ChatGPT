import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req12 {
    public static void main(String[] args) {
        try {
            // Message and the secret key
            String msg = "Hello, World!";
            String key = "secretKey";

            // Create a MAC instance with HmacSHA256
            Mac mac = Mac.getInstance("HmacSHA256");

            // Convert the key to bytes and then to a SecretKeySpec
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // Initialize the MAC with the key
            mac.init(secret_key);

            // Compute the MAC of the message
            byte[] macbytes = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));

            // Convert macbytes to Base64 String to make it human-friendly
            String result = Base64.getEncoder().encodeToString(macbytes);

            System.out.println("MAC: " + result); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}