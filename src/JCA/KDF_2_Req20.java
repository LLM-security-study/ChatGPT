import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_2_Req20 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your password:");
        String password = input.nextLine();

        char[] passwordCharArray = password.toCharArray();

        try {
           byte[] salt = new byte[16];

           KeySpec spec = new PBEKeySpec(passwordCharArray, salt, 65536, 128);
           SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
           byte[] hash = factory.generateSecret(spec).getEncoded();
           
           SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(hash, "AES");
           
           System.out.println("Secret Key: " + Arrays.toString(secretKey.getEncoded()));

        } catch (Exception e) {
            System.out.println("Exception Error: " + e.getMessage());
        }

    }
}