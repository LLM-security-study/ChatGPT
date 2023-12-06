import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KDF_1_Req12 {

    public static void main(String[] args) {
        String password = "Password123"; // A sample password.
        byte[] salt = new byte[16]; // Salt can be randomly generated.
        int iterationCount = 10000; // Iteration count
        int keyLength = 128; // Key length in bits

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] key = keyFactory.generateSecret(spec).getEncoded();
            // Now you have a cryptographic key derived from the user's password. 
            // The key is stored on "key" variable.
            // You can now use this key for cryptographic operations.

            // Displaying the key for demonstration purposes.
            for (byte b: key) {
                System.out.printf("%02X", b);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}