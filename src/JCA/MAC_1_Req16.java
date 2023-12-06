import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req16 {

    private static final String ALGORITHM = "HmacSHA256";

    public static void main(String[] args) {
        try {
            System.out.println("Enter your message:");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();

            System.out.println("Enter your key:");
            String key = sc.nextLine();

            System.out.println("MAC: " + generateMac(message, key));
        } catch (Exception e) {
            System.out.println("Exception generating MAC: " + e.getMessage());
        }
    }

    private static String generateMac(String message, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance(ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        sha256_HMAC.init(secretKey);

        byte[] macData = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        for (byte b : macData) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return result.toString();
    }
}