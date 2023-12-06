import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class KDF_1_Req6 {
  private static final int ITERATIONS = 10000;
  private static final int KEY_LENGTH = 256; 

  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your password: ");
    String password = scanner.nextLine();

    char[] passwordChars = password.toCharArray();
    byte[] saltBytes = getSalt();

    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
    byte[] keyBytes = skf.generateSecret(spec).getEncoded();

    String key = DatatypeConverter.printHexBinary(keyBytes);
    System.out.println("Your cryptographic key: " + key);
  }

  private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }
}