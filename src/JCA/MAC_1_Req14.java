import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req14 {
    private static final String SECRET_KEY = "secret-key"; // replace with your secret key
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message:");
        String message = scanner.nextLine();

        try {
            String mac = generateMac(message, SECRET_KEY);
            System.out.println("Generated MAC: " + mac);
        } catch (Exception e) {
            System.out.println("Error generating MAC: " + e);
        }
    }

    private static String generateMac(String message, String secretKey) throws Exception {
        Mac sha256Hmac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
        sha256Hmac.init(keySpec);

        byte[] macData = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        
        // Convert the bytes to Hex
        StringBuilder sb = new StringBuilder(macData.length * 2);
        for(byte b: macData) {
            sb.append(String.format("%02x", b & 0xff));
        }
        
        return sb.toString();

    }
} 