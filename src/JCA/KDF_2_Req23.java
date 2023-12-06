import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Scanner;

public class KDF_2_Req23 {
    public static void main(String[] args) throws Exception {
        // prompt user for password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // specify salt and iteration count for PBKDF2WithHmacSHA1
        byte[] salt = new byte[16]; // use a constant salt for this example
        int iterations = 10000; 

        // generate secret key
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey secretKey = factory.generateSecret(spec);

        // print secret key as hexadecimal
        byte[] secretKeyBytes = secretKey.getEncoded();
        String hex = DatatypeConverter.printHexBinary(secretKeyBytes);
        System.out.println("Secret Key: " + hex);
    }
}