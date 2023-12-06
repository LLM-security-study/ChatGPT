import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class MAC_1_Req8 {

  public static void main(String[] args) {
    try {
      // Initialize a Scanner to get user input
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter your message:");
      String message = scanner.nextLine();

      // The secret key used for the HMAC generation (must be kept secret)
      byte[] key = "secret".getBytes();

      // Create a MAC instance using HMAC-SHA256
      Mac mac = Mac.getInstance("HmacSHA256");

      // Initialize it with the secret key
      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
      mac.init(secretKeySpec);

      // Generate the HMAC for the given message
      byte[] hash = mac.doFinal(message.getBytes()); 

      // Print the generated HMAC in hexadecimal format
      System.out.println(bytesToHex(hash));

    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  // Method to convert a byte array into a hexadecimal string
  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}