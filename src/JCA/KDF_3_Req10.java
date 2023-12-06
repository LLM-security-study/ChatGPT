import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req10 {
    public static void main(String[] args) {
        String password = "password";
        String salt = "salty"; 
        int iterationCount = 65536;  
        int keyLength = 256;   
        
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterationCount, keyLength);
        SecretKey secret = null;
        try {
            secret = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        SecretKey secretKey = new SecretKeySpec(secret.getEncoded(), "AES");
        System.out.println(Arrays.toString(secretKey.getEncoded()));
    }
}