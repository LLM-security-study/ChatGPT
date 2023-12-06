import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_1_Req3 {

    public static void main(String[] args) {

        // User's password as entered by the user
        String password = "mySuperSecrretPassw0rd!";
        
        // A salt; in a real scenario, you should generate this salt and save it
        String salt = "myRandomlyGeneratedS@lt";

        try {
            // Create the key spec for password-based encryption
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 128);

            // Get the SecretKeyFactory for generating the secret key
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            // Generate the secret key
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // encode the raw byte representation of the key into Base64-encoded string
            String encodedKey = Base64.getEncoder().encodeToString(hash);

            System.out.println("Generated Cryptographic Key: " + encodedKey);
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}