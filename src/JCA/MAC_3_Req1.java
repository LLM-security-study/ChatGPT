import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class MAC_3_Req1 {
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    public static String computeMac(String data, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);

            byte[] rawMac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hmacBuilder = new StringBuilder();
            for (byte aRawMac : rawMac) {
                String hex = Integer.toHexString(0xFF & aRawMac);

                if (hex.length() == 1) {
                    hmacBuilder.append('0');
                }
                hmacBuilder.append(hex);
            }

            return hmacBuilder.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to compute mac", e);
        }
    }

    public static void main(String[] args) {
        String msg = "Hello World!";
        String secretKey = "SecretKey";
        String mac = computeMac(msg, secretKey);

        System.out.println("MAC of the message is: " + mac);
    }
}