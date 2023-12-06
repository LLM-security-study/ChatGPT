import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_1_Req1 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a password: ");
            char[] password = scanner.nextLine().toCharArray();

            byte[] salt = new byte[16]; // Ideally use a random salt.
            int iterationCount = 65536; // Use a minimum count of 64,000 for PBKDF2
            int keyLength = 128;
            
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            
            System.out.println("Generated AES key: " + Arrays.toString(secret.getEncoded()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}