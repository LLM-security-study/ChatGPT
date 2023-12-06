import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req21 {
    public static void main(String[] args) {
        String password = "password";
        byte[] salt = new byte[16]; // ideally this should be securely random and stored with the password

        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        byte[] hash = new byte[0];
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(hash));
    }
}