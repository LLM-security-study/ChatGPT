import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

public class KDF_2_Req27 {
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        System.out.println("Please enter your password:");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        byte[] salt = SALT.getBytes();
        SecretKey secretKey = generateSecretKey(password.toCharArray(), salt);
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Your secret key is: " + encodedKey);
    }

    private static SecretKey generateSecretKey(char[] password, byte[] salt) {
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
            KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}