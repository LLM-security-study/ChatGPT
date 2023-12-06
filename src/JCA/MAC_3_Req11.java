import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MAC_3_Req11 {
    private static final String secretKey = "secret-key"; // Replace with your secret key

    public static void main(String[] args) {
        String message = "Your message"; // Replace with your message
        String mac = generateMAC(secretKey, message);
        System.out.println("MAC: " + mac);
    }

    private static String generateMAC(String secretKey, String message) {
        try {
            byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            byte[] dataBytes = message.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
            
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            
            byte[] resultBytes = mac.doFinal(dataBytes);

            // Convert byte array to hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : resultBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}