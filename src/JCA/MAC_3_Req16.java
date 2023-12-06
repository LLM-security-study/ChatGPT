import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req16 {

    private static final String secretKey = "my-secret-key"; 
    private static final String message = "Hello, World!";

    public static void main(String[] args) {

        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), 
                "HmacSHA256");
            sha256HMAC.init(secretKeySpec);

            byte[] hashByte = sha256HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String hashString = Base64.getEncoder().encodeToString(hashByte);
            
            System.out.println("The MAC (Message Authentication Code) of the message '" 
                + message +"' is: " + hashString);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}