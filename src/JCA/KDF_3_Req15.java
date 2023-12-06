import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req15 {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "MySecretPassword"; // this could be user input
        byte[] salt = new byte[16]; // generate this randomly and store this with the password_hash
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        byte[] hash = f.generateSecret(spec).getEncoded();
        
        System.out.println("Password hash: "+Arrays.toString(hash));
    }
}