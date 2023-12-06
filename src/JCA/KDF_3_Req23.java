import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KDF_3_Req23 {

    public static void main(String[] args) {
        
        String password = "userpassword";
        byte[] salt = new byte[16]; 

        SecretKeyFactory factory = null;
        byte[] hash = new byte[0];

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // printing the generated key
            System.out.println(hash.toString());
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}