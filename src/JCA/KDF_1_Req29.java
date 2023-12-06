import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;
import java.util.Base64;

public class KDF_1_Req29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        byte[] salt = new byte[16]; // Use a random salt in real scenario
        // You can use SecureRandom to generate salt
        // SecureRandom.getInstanceStrong().nextBytes(salt);

        int iterationCount = 65536;
        int keyLength = 256;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);

        SecretKeyFactory factory = null;
        byte[] hash = null;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            assert factory != null;
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("Your hashed password is: " + Base64.getEncoder().encodeToString(hash));
    }
}