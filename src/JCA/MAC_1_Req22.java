import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req22 {
    public static void main(String[] args) {
        try {
            // Generate a Key
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();

            // Create a Mac instance
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);

            // Take message as input
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a message: ");
            String msg = sc.nextLine();

            // Compute the MAC
            byte[] macBytes = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(macBytes.length * 2);

            // Convert the MAC into hexadecimal
            for(byte b : macBytes) {
                sb.append(String.format("%02x", b&0xff));
            }

            System.out.println("The MAC is: " + sb.toString());
        } catch (Exception ex) {
            System.out.println("Error generating MAC: " + ex.getMessage());
        }
    }
}