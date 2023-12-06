import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req18 {
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter a message:");
      String message = scanner.nextLine();
      
      // Generate a secret key for the HmacSHA256 MAC algorithm.
      // You can custom the secret key to enhance security.
      String secret = "secretkey";
      SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(secretKeySpec);
      
      byte[] macData = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
      // Convert the MAC to a Hex String.
      StringBuilder sb = new StringBuilder();
      for (byte b : macData) {
        sb.append(String.format("%02x", b));
      }
      
      System.out.println("The MAC is:");
      System.out.println(sb.toString());
      
    } catch (Exception e) {
      System.out.println("Error generating MAC: " + e.getMessage());
    }
  }
}