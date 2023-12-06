import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_3_Req9 {

    public static void main(String[] args) {
        String message = "Test message";
        String secret = "Secret key";

        try {
            String macResult = calculateMac(secret, message);
            System.out.println("MAC: " + macResult);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private static String calculateMac(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] mac_data = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte mac_datum : mac_data) {
            result.append(Integer.toString((mac_datum & 0xff) + 0x100, 16).substring(1));
        }

        return result.toString();
    }
}