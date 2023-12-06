import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_3_Req30 {
    public static void main(String[] args) {
        String password = "YourPassword";
        byte[] salt = new byte[16]; // Use a secure random salt in production code

        SecretKeyFactory factory;
        byte[] hash = new byte[0];

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("Derived key: " + Base64.getEncoder().encodeToString(hash));
    }
}