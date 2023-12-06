import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your message: ");
        String message = scanner.nextLine();

        try {
            String result = generateMAC("HmacSHA256", message, "secretKey");
            System.out.println("The MAC is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateMAC(String algorithm, String message, String keyString) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(key);

        byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}