import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req30 {

  public static String getHashValue(String inputString) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] hashCode = md.digest(inputString.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : hashCode) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
       System.out.println("Invalid algorithm. " + ex.getMessage());
       return null;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a string: ");
    String inputString = sc.nextLine();
    String hashValue = getHashValue(inputString);
    System.out.println("The hash value is: " + hashValue);
  }
}