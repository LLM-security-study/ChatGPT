import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MAC_1_Req23 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message: ");

        String message = scanner.nextLine();

        try {
            // Generating a secret key for HMAC-MD5
            String secret = "secretkey";
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacMD5");

            // Get an instance of the HMAC-MD5
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKey);

            // Compute the MAC of input message
            byte[] bytes = mac.doFinal(message.getBytes());

            // Convert to base64
            String macBase64 = Base64.getEncoder().encodeToString(bytes);

            System.out.println("The MAC is: " + macBase64);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}