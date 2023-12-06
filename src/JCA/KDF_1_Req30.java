import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_1_Req30 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter a salt value");
        String salt = scanner.nextLine(); 

        try {
            byte[] saltBytes = salt.getBytes();
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65536, 256);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println("Derived cryptographic key from user's password is: " + Arrays.toString(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("An error occurred during deriving cryptographic key");
            e.printStackTrace();
        }
    }
}