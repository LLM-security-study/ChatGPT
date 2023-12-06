import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req25 {
    public static void main(String[] args) {
        String message = "This is a message";
        String secret = "This is a secret";
        
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] macData = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    
            // Convert byte array to hexadecimal string for a more readable format
            StringBuilder sb = new StringBuilder();
            for(byte b : macData) {
                sb.append(String.format("%02x", b));
            }
    
            // Print the MAC
            System.out.println("MAC: " + sb.toString());
    
            // Alternatively, print MAC as base64 string
            String base64mac = Base64.getEncoder().encodeToString(macData);
            System.out.println("MAC (base64): " + base64mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}