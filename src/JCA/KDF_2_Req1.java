import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.util.Arrays;

public class KDF_2_Req1 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "SomePassword"; // This should be provided by user

        // We use PBKDF2WithHmacSHA1 key generation algorithm and 1000 iteration count
        KeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[]{111,222,333,444,555,666,777,888}, 1000, 256);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = f.generateSecret(spec).getEncoded();

        System.out.println("Secret Key Generated from the provided password is : "+ Arrays.toString(key));
    }
}