import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        String secret = "secretKey"; // your secret key

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);
            byte[] macData = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String result = Base64.getEncoder().encodeToString(macData);
            System.out.println("MAC: " + result);
        } catch (Exception e) {
            System.out.println("Error generating MAC: " + e.getMessage());
        }
    }
}