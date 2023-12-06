import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_3_Req13 {
    // The secret key should be long and random for security.
    // It needs to be kept a secret to ensure that the MAC is secure.
    private static final String secretKey = "very_long_random_secret_key";

    public static void main(String[] args) {
        String message = "Hello, World!";
        try {
            // Print the MAC of the message
            System.out.println(
                "Message: " + message + 
                "\nMAC: " + calculateMAC(message)
            );
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String calculateMAC(String message) throws NoSuchAlgorithmException, InvalidKeyException {
        // Get an instance of the HMAC-SHA256 Mac
        Mac mac = Mac.getInstance("HmacSHA256");
        // Convert the secret key to bytes
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        // Create a secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
        // Initialize the mac with the secret key
        mac.init(secretKeySpec);
        // Convert the message to bytes
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        // Calculate the MAC
        byte[] macBytes = mac.doFinal(messageBytes);
        // Convert the MAC bytes to a base64 encoded string
        return Base64.getEncoder().encodeToString(macBytes);
    }
}