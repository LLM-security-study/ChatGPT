import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class KDF_3_Req4 {
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        char[] password = "mypassword".toCharArray();

        try {
            byte[] salt = generateSalt();
            SecretKey key = generateSecretKey(password, salt);
            System.out.println("Generated key: " + key.toString());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Error generating key: " + e.getMessage());
        }
    }

    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static SecretKey generateSecretKey(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKey tmp = factory.generateSecret(keySpec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}