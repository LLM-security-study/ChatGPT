import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MAC_1_Req24 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String message = scan.nextLine();

        System.out.println("MAC (Message Authentication Code): " + generateMac(message));
    }

    private static String generateMac(String message){
        try {
            // creating the byte array of secret key (example: "key12345")
            byte[] secretKey = "key12345".getBytes(StandardCharsets.UTF_8);

            // creating a Mac instance
            Mac mac = Mac.getInstance("HmacSHA256");

            // initializing the Mac object
            mac.init(new SecretKeySpec(secretKey, "HmacSHA256"));

            // performing the mac operation
            byte[] macbytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // encoding and returning the mac
            return Base64.getEncoder().encodeToString(macbytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}