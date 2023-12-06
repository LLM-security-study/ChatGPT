import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req2 {

    // Secret key - don't share this in a real application!
    private static final String SECRET_KEY = "some-secret-key";

    public static void main(String[] args) {
        String message = "The quick brown fox jumps over the lazy dog";
        try {
            String mac = calculateHMAC(message, SECRET_KEY);
            System.out.println("MAC: " + mac);
        } catch(Exception e) {
            System.err.println("Failed to calculate HMAC: " + e.getMessage());
        }
    }

    private static String calculateHMAC(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}