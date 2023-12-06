import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC_3_Req7 {
    public static void main(String[] args) {
        String message = "Test string";
        String secretKey = "SecretKey";

        try {
            byte[] mac = calculateHMAC(message, secretKey);
            System.out.println(bytesToHex(mac));
        } catch (Exception e) {
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }

    private static byte[] calculateHMAC(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}