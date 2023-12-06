import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req21 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String message = scanner.nextLine();

        String secret = "secret-key";

        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);

        String hash = bytesToHex(sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        System.out.println("The MAC (Message Authentication Code) is: " + hash);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}