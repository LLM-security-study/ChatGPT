import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_3_Req8 {

    public static void main(String[] args) {
        char[] password = "password".toCharArray();
        byte[] salt = new byte[16];

        int iterationCount = 65536;
        int keyLength = 128;

        KeySpec spec = new PBEKeySpec(password, salt, iterationCount, keyLength);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            System.out.println(Arrays.toString(hash));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }  
    }
}