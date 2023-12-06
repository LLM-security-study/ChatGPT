import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_2_Req13 {

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final String secretKey = "YourSecretKey";

    public static void main(String[] args) {
        String message = "Hello, World!";
        try {
            System.out.println("MAC: " + calculateHMAC(message, secretKey));
        } catch (Exception e) {
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }

    private static String calculateHMAC(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}