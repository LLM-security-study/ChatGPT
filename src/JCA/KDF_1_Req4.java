import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_1_Req4 {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;
    private static final byte[] SALT = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};

    public static void main(String[] args) {
        System.out.println("Input Password: " + args[0]);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec spec = new PBEKeySpec(args[0].toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
            SecretKey secret = factory.generateSecret(spec);
            byte[] raw = secret.getEncoded();

            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(raw));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}