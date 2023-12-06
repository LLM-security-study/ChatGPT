import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class KDF_2_Req24 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a password:");
        String password = scanner.next();

        //Salt can be stored or generated as per the requirement
        byte[] salt = new byte[16];

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = f.generateSecret(spec).getEncoded();
        System.out.println("Secret key: " + Arrays.toString(hash));
    }
}