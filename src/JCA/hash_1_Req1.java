import java.security.MessageDigest;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req1 {
  
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the string:");
      String text = sc.nextLine();
      
      try {
          MessageDigest md = MessageDigest.getInstance("SHA-256");
          byte[] hash = md.digest(text.getBytes());

          StringBuilder sb = new StringBuilder();
          for (byte b : hash) {
              sb.append(String.format("%02x", b));
          }
          System.out.println("The hash value is:");
          System.out.println(sb.toString());
      } catch (NoSuchAlgorithmException e) {
          System.out.println("Error: " + e);
      }
  }
}