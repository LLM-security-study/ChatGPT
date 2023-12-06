import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_1_Req25 {

    private static final String HMAC_SHA256 = "HmacSHA256";

    public static String calculateHMAC(String data, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac", e);
        }
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: MAC_1_Req25 <data> <key>");
            System.exit(1);
        }
        String data = args[0];
        String key = args[1];
        String mac = calculateHMAC(data, key);
        System.out.println("MAC: " + mac);
    }
}