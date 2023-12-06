// import required classes
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC_3_Req14 {
    public static void main(String[] args) {
        try {
            // input message
            String message = "This is a message for MAC.";
            // secret key
            String secret = "secret";

            Mac hasher = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            hasher.init(secretKey);

            byte[] hashedBytes = hasher.doFinal(message.getBytes());
            String encodedHash = Base64.getEncoder().encodeToString(hashedBytes);
            // print the encoded MAC hash
            System.out.println(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
        }
    }
}