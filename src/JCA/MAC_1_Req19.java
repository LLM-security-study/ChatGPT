import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC_1_Req19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a message to generate a MAC:");
        String message = scanner.nextLine();

        // Key to be used for MAC generation
        String keyString = "secretkey";
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");

        try {
            // Fetching Mac instance for HmacSHA256
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);

            // Computing the Message Authentication Code (MAC)
            byte[] bytes = mac.doFinal(message.getBytes());

            // Convert MAC to String for display
            String macString = Base64.getEncoder().encodeToString(bytes);

            System.out.println("The MAC for the given message is: " + macString);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("MAC Generation Error: " + e.getMessage());
        }

        scanner.close();
    }
}