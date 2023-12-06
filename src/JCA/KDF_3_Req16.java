import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req16 {
    public static void main(String[] args) {

        char[] password = "user_password".toCharArray();
        byte[] salt = new byte[16]; // this should be stored with the password

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    
            // Parameters: char array of user password, salt, iteration count, derived key length
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 128); 
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // conveniently print the derived key
            System.out.println(Arrays.toString(hash));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}