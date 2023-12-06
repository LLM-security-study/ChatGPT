import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class KDF_1_Req11 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        System.out.println("Enter salt value (should be long & complex for security): ");
        String salt = scanner.nextLine();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        byte[] secret = factory.generateSecret(spec).getEncoded();

        System.out.println("Derived cryptographic keys from user's password: " + Arrays.toString(secret));
    }
}