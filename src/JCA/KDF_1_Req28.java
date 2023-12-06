import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_1_Req28 {
  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
    char[] password = "password".toCharArray();
    byte[] salt = new byte[16];

    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
    byte[] hash = factory.generateSecret(spec).getEncoded();

    System.out.println(Arrays.toString(hash));
  }
}