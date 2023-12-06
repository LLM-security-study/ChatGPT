import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC_2_Req26 {

    private static final String secretKey = "your-very-secure-and-inaccessible-key";

    public static void main(String[] args) {
        String message = "Hello, this is a secret message!";
        String macResult = calculateHMAC(message, secretKey);
        System.out.println("MAC result: " + macResult);
    }

    private static String calculateHMAC(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secretKey);

            byte[] rawHmac = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
}