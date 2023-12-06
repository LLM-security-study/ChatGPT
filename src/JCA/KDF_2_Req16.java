import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class KDF_2_Req16 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter password:");
        String password = input.nextLine();

        try {
            // Generate a SecretKey from the given password
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), "randomSalt".getBytes(), 65536, 128);
            SecretKey key = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(key.getEncoded(), "AES");
            
            // Print the secret key
            System.out.println("Secret key: " + Base64.getEncoder().encodeToString(secret.getEncoded()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}