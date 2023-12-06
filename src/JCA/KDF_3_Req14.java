import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req14 {

    public static void main(String[] args) {
        try {
            String password = "password";
            byte[] salt = new byte[16];
            int iterationCount = 10000;
            int keyLength = 256;

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println(Arrays.toString(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}