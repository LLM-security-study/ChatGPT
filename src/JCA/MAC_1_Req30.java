import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message: ");
        String message = scanner.nextLine();
        String keyString = "secret";

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : macBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            System.out.println("MAC: " + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}