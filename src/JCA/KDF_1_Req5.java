import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.security.SecureRandom;

public class KDF_1_Req5 {
    public static void main(String[] args) {

        try {
            // Password entered by the user
            String password = "user_password";

            // Generate Salt
            byte[] salt = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);

            // Derive the key
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = f.generateSecret(spec).getEncoded();

            // Print the key
            System.out.println(Base64.getEncoder().encodeToString(hash));
        } 
        catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}