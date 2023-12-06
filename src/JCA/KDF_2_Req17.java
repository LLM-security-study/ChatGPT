import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

public class KDF_2_Req17 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        byte[] salt = new byte[16]; // Use a salt in practice!

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128); // 128 bit
        SecretKeyFactory factory = null;
        byte[] secretKey = new byte[0];
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            secretKey = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("Your secret key:");
        System.out.println(Base64.getEncoder().encodeToString(secretKey));
    }
}