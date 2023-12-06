import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req4 {
    public static void main(String[] args) {
        String secretKey = "secretKey";
        String message = "Hello, World!";

        try {
            // Get an instance of the HMAC MD5 Mac
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            // Create SecretKeySpec
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // Initialize the Mac
            sha256_HMAC.init(secret_key);

            // Perform the Mac
            byte[] mac_data = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert to String and Encode it
            String result = Base64.getEncoder().encodeToString(mac_data);

            System.out.println("Message Authentication Code: " + result);
        } catch(Exception e) {
            System.out.println("Error");
        }
    }
}