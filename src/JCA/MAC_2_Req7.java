import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC_2_Req7 {

    private static Mac mac;

    public static void main(String[] args) {
        String secretKey = "my-secret-key";
        String message = "Hello, World!";
        
        macSetup(secretKey);

        byte[] mac = generateMac(message);

        System.out.println(bytesToHex(mac));
    }

    private static void macSetup(String secretKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateMac(String message) {
        return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}