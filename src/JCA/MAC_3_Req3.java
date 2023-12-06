import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC_3_Req3 {
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static void main(String[] args) {
        String key = "secret_key"; 
        String message = "Hello World";

        try {
            System.out.println(calculateHMAC(message, key));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String calculateHMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}