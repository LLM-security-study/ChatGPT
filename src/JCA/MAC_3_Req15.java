import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req15 {

    public static void main(String[] args) throws Exception {

        String secret = "secret-key";
        String message = "message";

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));

        System.out.println("MAC: " + hash);
    }
}