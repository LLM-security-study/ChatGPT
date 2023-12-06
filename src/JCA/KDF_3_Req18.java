import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req18 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "user_password";
        byte[] salt = new byte[16]; 

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        byte[] hash = factory.generateSecret(spec).getEncoded();

        System.out.println("Derived key: " + Arrays.toString(hash));
    }
}