import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req17 {
    public static void main(String[] args) {
        try {
            // Reading message from user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your message:");
            String message = scanner.nextLine();

            // Secret Key
            final String secretKey = "Thisisasamplekey";

            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256HMAC.init(keySpec);

            // Generating MAC
            byte[] macData = sha256HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Generating hexadecimal MAC
            StringBuilder result = new StringBuilder();
            for (final byte element : macData) {
                result.append(Integer.toHexString((element & 0xff) | 0x100), 1, 3);
            }

            System.out.println("MAC: " + result.toString());

        } catch (Exception e){
            System.out.println("Error while generating MAC: " + e.getMessage());
        }
    }
}