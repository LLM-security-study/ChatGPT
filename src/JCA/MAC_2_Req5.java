import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req5 {
    public static void main(String[] args) {
        String secretKey = "ThisIsASecretKey";
        String message = "This is the message that needs to be checked for integrity.";

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);

            byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder(macBytes.length * 2);
            for (byte b : macBytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
+                    result.append('0');
                }
                result.append(hex);
            }
            
            System.out.println("Message: " + message);
            System.out.println("MAC: " + result.toString());
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}