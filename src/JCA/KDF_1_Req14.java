import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_1_Req14 {
    public static void main(String[] args) {
        // Prompt user for password input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        // Generate salt for password hashing (you should save this)
        // For this example, we're using a constant salt.
        byte[] salt = new byte[16];

        SecretKeyFactory factory = null;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKey tmp = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            tmp = factory.generateSecret(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        System.out.println("Derived key: " + Arrays.toString(secret.getEncoded()));
    }
}