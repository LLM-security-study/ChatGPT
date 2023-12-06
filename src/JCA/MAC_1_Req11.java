import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message: ");
        String message = scanner.nextLine();

        try {
            // Get an instance of the HMAC-SHA256 MAC algorithm.
            Mac mac = Mac.getInstance("HmacSHA256");

            // Initialize the MAC with a secret key.
            byte[] secretKeyBytes = "YourSecretKey".getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
            mac.init(secretKeySpec);

            // Compute the MAC.
            byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert the MAC bytes to hexadecimal.
            StringBuilder sb = new StringBuilder(macBytes.length * 2);
            for (byte b : macBytes) {
                sb.append(String.format("%02x", b));
            }
            String macHex = sb.toString();

            System.out.println("MAC: " + macHex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}