import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MAC_2_Req4 {
    public static void main(String[] args) {
        try {
            // Message and key
            String message = "This is a test message";
            String key = "secretEncryptionKey";

            Mac hasher = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");

            hasher.init(keySpec);
            byte[] hashedMessage = hasher.doFinal(message.getBytes());

            String encodedHash = Base64.getEncoder().encodeToString(hashedMessage);

            System.out.printf("MAC: %s%n", encodedHash);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.err.println("Error generating MAC: " + e.getMessage());
        }
    }
}