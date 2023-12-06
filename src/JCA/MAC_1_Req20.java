import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();

        String secret = "your_secret_key"; // replace "your_secret_key" with your actual secret key

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);
            byte[] encodedHash = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(encodedHash);

            System.out.println("The HMAC SHA256 Hash is: " + hash);
        } catch (Exception e) {
            System.out.println("Error generating HMAC SHA256");
        }
    }
}