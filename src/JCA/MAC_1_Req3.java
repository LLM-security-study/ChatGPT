import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_1_Req3 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the secret message:");
        String message = scanner.nextLine();

        System.out.println("Enter the secret key:");
        String secretKey = scanner.nextLine();

        SecretKeySpec keySpec = new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);
        byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        System.out.println("MAC: " + bytesToHex(macBytes));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}