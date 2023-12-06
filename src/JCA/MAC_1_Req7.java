import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message:");
        String message = scanner.nextLine();

        try {
            String secretKey = "secret";
            Mac mac = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);

            byte[] messageHMAC = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encodedHMAC = Base64.getEncoder().encodeToString(messageHMAC);

            System.out.println("The HMAC for the message is:");
            System.out.println(encodedHMAC);
        } catch (Exception e) {
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }
}