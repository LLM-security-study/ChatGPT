import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class KDF_3_Req20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter salt: ");
        String salt = sc.nextLine();

        try {
            byte[] btSalt = salt.getBytes();
            int iterations = 10000;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), btSalt, iterations, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println("Derived key: " + toHex(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static String toHex(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }
}