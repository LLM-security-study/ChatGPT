import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_3_Req8 {
    public static void main(String[] args) {
        String secret = "secret-key";
        String message = "This is a message";

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] hmacSHA256 = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder resultHash = new StringBuilder();
            for (byte b : hmacSHA256) {
                resultHash.append(String.format("%02X", b));
            }
            System.out.println("HMAC SHA256: " + resultHash.toString());

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Error while creating HMAC SHA256 "+e.getMessage());
        }
    }
}