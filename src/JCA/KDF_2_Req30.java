import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class KDF_2_Req30 {
    public static void main(String[] args) {
        // Get password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Generate secret key
        SecretKey key = generateSecretKey(password);
        System.out.println("Your secret key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
    }

    public static SecretKey generateSecretKey(String password) {
        try {
            int iterationCount = 65536; // number of iterations
            int keyLength = 256; // key length in bits
            byte[] salt = new byte[8]; // salt length. (must be atleast 8 bytes according to PKCS#5 standard)

            // Generate a random salt
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            // Generate SecretKey factory 
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);

            SecretKey secretKey = factory.generateSecret(spec);
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}