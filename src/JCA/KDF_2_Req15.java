import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class KDF_2_Req15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        KeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[16], 65536, 128);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] secretKey = new byte[0];
        try {
            secretKey = skf.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        String secretKeyStr = new String(secretKey);
        System.out.println("Secret key : " + secretKeyStr);
    }
}