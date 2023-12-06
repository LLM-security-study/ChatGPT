import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MAC_1_Req29 {

    private static final String key = "secret"; // Add your key here

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String message = sc.next();
        System.out.println("MAC result: " + computeHmacSHA256(message, key));
    }

    private static String computeHmacSHA256(String message, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hasher = Mac.getInstance("HmacSHA256");
        hasher.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));

        byte[] hash = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // Convert byte array into a readable format
        StringBuilder builder = new StringBuilder();
        for (byte b : hash) {
            builder.append(Integer.toHexString(0xFF & b));
        }
        return builder.toString();
    }
}