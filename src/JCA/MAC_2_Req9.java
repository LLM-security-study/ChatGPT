import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req9 {
    public static void main(String[] args) {
        String msg = "Hello, World!";
        String keyString = "secretkey";
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);

        try {
            // Initializing the MAC and the key spec
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            // Perform the MAC
            byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
            byte[] resultBytes = mac.doFinal(msgBytes);

            // Convert to hex for readability
            StringBuilder sb = new StringBuilder(resultBytes.length * 2);
            for(byte b: resultBytes) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("MAC result: " + sb.toString());

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}