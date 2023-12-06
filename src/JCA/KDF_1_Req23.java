import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class KDF_1_Req23 {
    public static void main(String[] args) {
        String passwordToHash = "replace_with_your_password";
        int iterations = 100;
        char[] chars = passwordToHash.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 256);
        Arrays.fill(chars, '0');

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            System.out.println("PBKDF2WithHmacSHA256 hashed password: " + toHex(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getSalt() {
        // Here, we are simply returning a fixed salt for consistency.
        // In a real application, you should generate a new random salt for each password
        return new BigInteger("00f8d6038a209c1613c6d475587392c9", 16).toByteArray();
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}