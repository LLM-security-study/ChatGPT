import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req6 {

    public static void main(String[] args) {
        char[] password = "secret".toCharArray();
        byte[] salt = new byte[16]; // recommended salt length. In production level, use a better way to produce salt.

        SecretKeyFactory factory = null;
        byte[] hash = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 256); // 256 bit derived key
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(hash));  // print derived key.
    }
}