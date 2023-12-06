import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class MAC_3_Req5 {
    public static void main(String[] args) {
        String message = "Hello, World!";
        String key = "secret";
        String macValue = calculateMAC(message, key);
        System.out.println("The MAC value is: " + macValue);
    }

    public static String calculateMAC(String message, String secret) {
        try {
            // create a MAC object using HMAC-MD5 algorithm
            Mac mac = Mac.getInstance("HmacMD5");

            // create a secret key
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacMD5");

            // initialize the MAC object with the secret key
            mac.init(secretKey);

            // compute the MAC of the message
            byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // convert the bytes to hexadecimal
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}