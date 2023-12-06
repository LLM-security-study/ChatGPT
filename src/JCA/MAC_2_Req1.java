import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req1 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // Step 1: Get the Key
        String secret = "secret-key";
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        // Step 2: Create the MAC
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        
        // Step 3: Compute the MAC on input data
        byte[] bytes = mac.doFinal("Hello, World!".getBytes(StandardCharsets.UTF_8));

        //Step 4: To Hexadecimal form
        StringBuilder hash = new StringBuilder();
        for (byte b : bytes) {
            hash.append(String.format("%02x", b));
        }

        System.out.println(hash);
    }
}