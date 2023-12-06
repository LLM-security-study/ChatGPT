import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class KDF_1_Req7 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            // Here salt is hard-coded for the sake of simplicity. Do use a secure random salt for actual use
            byte[] salt = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKey secretKey = factory.generateSecret(spec);
            byte[] key = secretKey.getEncoded();

            System.out.println("The generated key from your password is: ");
            for (byte b : key) {
                System.out.printf("%02X", b);
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}