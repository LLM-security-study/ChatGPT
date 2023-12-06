import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req24 {
    public static void main(String[] args){

        String secret = "secret-key";
        String message = "Hello, World!";
        
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256HMAC.init(secretKey);
    
            byte[] bytes = sha256HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String mac = Base64.getEncoder().encodeToString(bytes);
    
            System.out.println("MAC: " + mac);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}