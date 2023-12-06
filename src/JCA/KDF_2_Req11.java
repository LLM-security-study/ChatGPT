import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.security.spec.*;

public class KDF_2_Req11 {
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] password = "MyPassword123".toCharArray();
        byte[] salt = new byte[16];

        // Generate random salt
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        SecretKey secretKey = generateSecretKey(password, salt);

        // print secret key
        System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
    }

    private static SecretKey generateSecretKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKey tmp = secretKeyFactory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}