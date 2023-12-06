import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MAC_3_Req18 {
  public static void main(String[] args) {
    try {
      String secretKey = "your secret key"; //Replace with your secret key
      String message = "your message"; //Replace with your message

      Mac hasher = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
      hasher.init(secretKeySpec);

      byte[] hashedBytes = hasher.doFinal(message.getBytes());
      String base64Hash = Base64.getEncoder().encodeToString(hashedBytes);

      System.out.println("The MAC is: " + base64Hash);
    } 
    catch (NoSuchAlgorithmException e) {
      System.out.println("No such algorithm exception: " + e.getMessage());
    } 
    catch (InvalidKeyException e) {
      System.out.println("Invalid key exception: " + e.getMessage());
    }
  }
}