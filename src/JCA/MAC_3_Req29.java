import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC_3_Req29 {
    private static final String secretKey = "secretkey";

    public static void main(String[] args) {
        String msg = "This message needs to be authenticated";
        try {
            String mac = calculateMac(secretKey, msg);
            System.out.println("Generated MAC: " + mac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Error generating MAC: " + e.getMessage());
        }
    }

    private static String calculateMac(String secretKey, String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);

        byte[] textBytes = msg.getBytes(StandardCharsets.UTF_8);
        byte[] macBytes = mac.doFinal(textBytes);

        byte[] encodedBytes = Base64.getEncoder().encode(macBytes);
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}