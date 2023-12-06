import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req6 {
    private static final String secret = "verysecretkey"; // Your secret key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message:");
        String message = scanner.nextLine();

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] hashedBytes = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(hashedBytes);
            
            System.out.println("The MAC is: " + hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}