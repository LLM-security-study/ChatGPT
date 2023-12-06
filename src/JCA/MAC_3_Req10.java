import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC_3_Req10 {
    private static final String algorithm = "HmacSHA256";

    public static void main(String[] args) {
        String message = "This is a secret message";
        String secret = "secretKey";
        String computedMAC = computeMAC(message, secret);
        System.out.println("Computed MAC: " + computedMAC);
    }

    private static String computeMAC(String message, String secret) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), algorithm);
        Mac mac;
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to calculate hmac", e);
        }
    }
}