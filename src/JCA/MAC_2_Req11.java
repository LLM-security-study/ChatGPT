import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class MAC_2_Req11 {
    public static void main(String[] args) throws Exception {
        final String originalMessage = "This is the original message";
        final String hmacSha256 = "HmacSHA256";
        SecretKeySpec secretKeySpec = new SecretKeySpec(hmacSha256.getBytes(), hmacSha256);

        Mac mac = Mac.getInstance(hmacSha256);
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(originalMessage.getBytes());

        System.out.println(Arrays.toString(hmacBytes));

        // Now validate the integrity of the message
        String newMessage = "This is the original message";
        mac.init(secretKeySpec);
        byte[] newHmacBytes = mac.doFinal(newMessage.getBytes());

        if (Arrays.equals(hmacBytes, newHmacBytes)) {
            System.out.println("Message is valid");
        } else {
            System.out.println("Message is not valid");
        }
    }
}