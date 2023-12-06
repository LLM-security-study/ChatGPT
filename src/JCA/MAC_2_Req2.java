import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req2 {

    public static void main(String[] args) throws Exception {
        String secretKey = "thisisasecretkey"; // Set your secret key here
        String message = "This is the message to check integrity of"; // Set your message here

        try {
            byte[] macResult = calculateMAC(secretKey.getBytes(), message.getBytes());
 
            System.out.println(bytesToHex(macResult));

        } catch (InvalidKeyException | NoSuchAlgorithmException ex) {
            System.out.println("Error creating MAC: " + ex.getMessage());
        }
    }

    static byte[] calculateMAC(byte[] secretKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        // Get an instance of the HMAC-SHA256 Mac
        Mac mac = Mac.getInstance("HmacSHA256");

        // Initialize the Mac with the secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
        mac.init(secretKeySpec);

        // Calculate the HMAC
        return mac.doFinal(message);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}