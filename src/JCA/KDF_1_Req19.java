import java.util.Arrays;
import java.security.spec.KeySpec;
import java.security.MessageDigest;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KDF_1_Req19 {
    public static void main(String[] args) throws Exception {
        char[] password = "YourUserPassword".toCharArray();
        byte[] salt = "YourSalt".getBytes();
        
        // Create a PBEKeySpec
        int keyLength = 256;
        int iterations = 65536;
        KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = keyFactory.generateSecret(spec).getEncoded();
        
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        System.out.println("Generated Secret Key: " + Arrays.toString(secretKey.getEncoded()));
    }
}