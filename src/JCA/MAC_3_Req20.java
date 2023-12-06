import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req20 {
    
    public static void main(String[] args) {
        String secretKey = "your_secret_key";
        String message = "Your message";
        
        try {
            String macResult = calculateHMAC(secretKey, message);
            System.out.println("MAC result: " + macResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String calculateHMAC(String secret, String message) throws Exception {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretBytes, "HmacSHA256");
        
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] resultBytes = mac.doFinal(messageBytes);
        
        return Base64.getEncoder().encodeToString(resultBytes);
    }
}