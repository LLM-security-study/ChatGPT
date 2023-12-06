import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
 
public class MAC_2_Req14 {

    private static final String HMAC_SHA256 = "HmacSHA256";

    private static byte[] createHmac(String key, String message) throws Exception {
        byte[] bytesKey = key.getBytes();
        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKey);
        return mac.doFinal(message.getBytes());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String secretKey = "mySecretKey";
            String message = "Hello, world!";

            System.out.println("Original Message: " + message);
            String hmac = bytesToHex(createHmac(secretKey, message));
            System.out.println("HMAC: " + hmac);
        } 
        catch (Exception e) {
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }
}