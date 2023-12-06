import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Scanner;
import java.util.Arrays;

public class KDF_1_Req24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your password:");
        String password = sc.nextLine();

        int iterations = 10000; // number of iterations, larger is better but slower
        int keyLength = 256; // key length in bits, typical values are 128, 192, 256
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = new byte[] {3, 1, 4, 1, 5, 9, 2, 6}; // ideally random and stored separately

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(spec).getEncoded();
            System.out.println("Key: " + Arrays.toString(keyBytes));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}