import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MAC_2_Req18 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // Initialize a secret key. In practice this should be securely exchanged between parties
        byte[] secretKey = "some secret".getBytes();

        // The message we want to check the integrity of
        byte[] message = "Hello, World!".getBytes();

        // Create a Mac instance using HMAC-SHA256
        Mac mac = Mac.getInstance("HmacSHA256");

        // Initialize the Mac with the secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
        mac.init(secretKeySpec);

        // Perform the integrity check
        byte[] macData = mac.doFinal(message);

        System.out.println(Arrays.toString(macData));
    }
}