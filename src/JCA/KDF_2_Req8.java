import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.util.Scanner;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_2_Req8 {
    public static void main(String[] args) {
        try {
            // Obtain password from user
            System.out.println("Enter your password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            scanner.close();
            
            // Define salt and iteration count for the SecretKey generation
            byte[] salt = new byte[16]; // Normally you should use a securely randomly generated salt
            int iterationCount = 65536;
            int keyLength = 256;

            // Generate SecretKey
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey secretKey = factory.generateSecret(spec);

            System.out.println("Secret Key has been generated");
            System.out.println("Secret Key in base 64 encoding: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}