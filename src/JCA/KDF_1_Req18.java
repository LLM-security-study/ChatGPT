import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class KDF_1_Req18 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Please enter your password: ");
    String password = in.nextLine();

    byte[] salt = new byte[16]; // ideally this should be securely random and stored with the password.

    SecretKeyFactory factory;
    byte[] hash = null;
    try {
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
      hash = factory.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }

    System.out.println("The derived key is: " + Base64.getEncoder().encodeToString(hash));
  }
}