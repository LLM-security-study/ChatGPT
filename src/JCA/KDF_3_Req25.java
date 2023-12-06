import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req25 {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
      
        String passwordToHash = "123456";
        String salt = "YOUR_SALT";
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(passwordToHash.toCharArray(), salt.getBytes(), 65536, 256);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        System.out.println(Arrays.toString(hash));
    }
}