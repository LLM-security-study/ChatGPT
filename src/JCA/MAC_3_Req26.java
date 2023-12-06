import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req26 {
    public static void main(String[] args) {
        String secret = "secret-key";
        String message = "Hello, World!";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8)));
            System.out.println(hash);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
}