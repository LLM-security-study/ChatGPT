import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_3_Req22 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the password:");
    String password = scanner.next();

    System.out.println("Please enter the salt:");
    String salt = scanner.next();

    int iterations = 10000;
    int keyLength = 512;
    char[] passwordChars = password.toCharArray();
    byte[] saltBytes = salt.getBytes();

    PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);

    Arrays.fill(passwordChars, Character.MIN_VALUE);

    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      byte[] securePassword = skf.generateSecret(spec).getEncoded();
      System.out.println(Arrays.toString(securePassword));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
    } finally {
      spec.clearPassword();
    }
  }
}