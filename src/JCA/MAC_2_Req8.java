import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req8 {
    public static void main(String[] args) {
        try {
            // Key and Data to be encrypted
            String secret = "secret-key";
            String message = "Hello, World!";
            
            // Create a MAC instance
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            
            // Perform MAC operation on the given message
            byte[] bytes = mac.doFinal(message.getBytes());
            
            // Convert byte array to hex format
            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }
            System.out.println("Message Authentication Code: " + hash.toString());
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            // Handle exceptions appropriately for your application
            e.printStackTrace();
        }
    }
}