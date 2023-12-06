import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_1_Req13 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a message:");
            String message = scanner.nextLine();

            // Secret Key
            String secret = "secretkey";
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secret_key);
            byte[] bytes = mac.doFinal(message.getBytes("UTF-8"));

            // Convert bytes array to hexadecimal
            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }

            System.out.println("The MAC is: " + hash.toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            System.out.println("Error generating MAC: " + e.getMessage());
        }
    }
}