import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_1_Req9 {

    private static final String secretKey = "anySecretKey";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String message = sc.nextLine();

        try {
            String mac = generateMAC(secretKey, message);
            System.out.println("The generated MAC is: " + mac);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.toString());
        }
    }

    private static String generateMAC(String secretKey, String message) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);
        byte[] rawMac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        byte[] hexFormat = new String(Base64.getEncoder().encode(rawMac)).getBytes(StandardCharsets.UTF_8);

        return new String(hexFormat);
    }
}