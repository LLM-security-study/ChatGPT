import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message:");
        String message = scanner.nextLine();

        System.out.println("Enter a secret key:");
        String secret = scanner.nextLine();
        
        try {
            Mac mac = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);

            byte[] rawMac = mac.doFinal(message.getBytes());
            byte[] base64EncodedMac = Base64.getEncoder().encode(rawMac);

            System.out.println("MAC for given message is : "+ new String(base64EncodedMac));
            
        } catch (Exception e) {
            System.out.println("Error generating MAC: " + e.getMessage());
        }
    }
}