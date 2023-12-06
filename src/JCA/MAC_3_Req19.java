import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class MAC_3_Req19 {
  
  private static final String SECRET_KEY = "ThisIsASecretKey";
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter message: ");
    String message = scanner.nextLine();

    try {
      String mac = calculateMac(message);
      System.out.println("MAC: " + mac);
    } catch (Exception e) {
      System.out.println("Error generating MAC: " + e.getMessage());
    }
  }

  /**
   * Calculates the MAC of a given message.
   *
   * @param message The message to calculate the MAC of.
   * @return The MAC as a hexadecimal string.
   * @throws Exception If an error occurs during calculation.
   */
  public static String calculateMac(String message) throws Exception {
    Mac sha256Hmac = Mac.getInstance("HmacSHA1");
    SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
    sha256Hmac.init(secretKeySpec);
    byte[] macData = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));

    // Convert the bytes to hexadecimal
    return Base64.getEncoder().encodeToString(macData);
  }
}