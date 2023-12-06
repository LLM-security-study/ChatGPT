import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req29 {

    private static final String SECRET_KEY = "secret-key";
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static void main(String[] args) {
        String message = "Hello World!";
        try {
            String macValue = calculateHMAC(message, SECRET_KEY);
            System.out.println("MAC value for " + message + " is " + macValue);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String calculateHMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}