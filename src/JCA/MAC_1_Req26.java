import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the message: ");
        String message = scanner.nextLine();
        System.out.println("Please enter the key: ");
        String key = scanner.nextLine();
        try {
            String macValue = calculateHMAC(message, key);
            System.out.println("Generated MAC using HMAC-SHA256: " + macValue);
        } catch (Exception e) {
            System.out.println("Failed to generate MAC due to: " + e.getMessage());
        }
    }

    public static String calculateHMAC(String data, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(
                key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);

        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(rawHmac);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}