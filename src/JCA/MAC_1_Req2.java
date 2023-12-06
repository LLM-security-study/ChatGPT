import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message to be authenticated:");
        String message = scanner.nextLine();

        String secretKey = "my-very-secret-key"; // This key should be kept secret and secure

        try {
            System.out.println("Generated MAC: " + generateMAC(secretKey, message));
        } catch (Exception e) {
            System.out.println("An error occurred while generating MAC: " + e.getMessage());
        }
    }

    public static String generateMAC(String secret, String message) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte aRawHmac : rawHmac) {
            sb.append(String.format("%02X", aRawHmac));
        }
        return sb.toString();
    }
}