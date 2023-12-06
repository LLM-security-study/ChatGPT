import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req3 {

    private static final String salt = "salty";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] password = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };
        
        byte[] derivedKey = deriveKey(password);

        System.out.println("Key: " + toHex(derivedKey));
        
        Arrays.fill(password, '0');
    }
    
    private static byte[] deriveKey(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password, salt.getBytes(), 65536, 128);
    
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        return f.generateSecret(spec).getEncoded();
    }

    private static String toHex(byte[] bytes) {
 
        StringBuilder result = new StringBuilder();
        
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        
        return result.toString();
    }
}