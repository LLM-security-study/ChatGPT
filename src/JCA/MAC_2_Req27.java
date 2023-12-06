import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MAC_2_Req27 {

    public static void main(String[] args) throws Exception {
        String msg = "Hello World!";
        String key = "SecretKey";

        byte[] macResult = generateMac(key, msg);

        System.out.println("Generated MAC: " + Arrays.toString(macResult));
    }

    public static byte[] generateMac(String key, String message) throws Exception {
        byte[] bytesKey = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);

        byte[] bytesMessage = message.getBytes(StandardCharsets.UTF_8);

        return mac.doFinal(bytesMessage);
    }
}