import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.security.*;

public class KDF_2_Req12 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        try {
            // Create salt
            byte[] salt = new byte[16];
            SecureRandom sr = SecureRandom.getInstanceStrong();
            sr.nextBytes(salt);

            // Create SecretKey using PBEKeySpec
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256); 
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey secretKey = skf.generateSecret(spec);
            byte[] secretKeyBytes = secretKey.getEncoded(); 

            // Print the Secret Key
            System.out.println("Generated Secret Key from your password: " + Arrays.toString(secretKeyBytes));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
           System.out.println("Error generating secret key: " + e.getMessage());
        }
    }
}