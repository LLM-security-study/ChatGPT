import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KDF_3_Req13 {

    public static void main(String[] args) {
        String password = "password123";
        String salt = "salt123";
        int iterations = 1000;
        int keyLength = 128;

        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        String hashedPassword = String.format("%x", new BigInteger(hashedBytes));

        System.out.println(hashedPassword);
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            KeySpec keySpec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] secretKey = factory.generateSecret(keySpec).getEncoded();
            return secretKey;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing a password: " + e.getMessage(), e);
        }
    }
}