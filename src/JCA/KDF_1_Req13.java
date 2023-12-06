import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class KDF_1_Req13 {

    public static void main(String[] args) {
        try {
            System.out.println("Enter the password from which a key will be derived");
            Scanner input = new Scanner(System.in);
            String password = input.nextLine();

            System.out.println("Enter the salt which should be kept secret");
            String salt = input.nextLine();

            // Recommended iterations for PBKDF2
            int iterations = 10000;
            // Output key length
            int outputKeyLength = 256;
            
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec passwordBasedEncryptionKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, outputKeyLength);
            SecretKey secretKey = secretKeyFactory.generateSecret(passwordBasedEncryptionKeySpec);

            System.out.println("The generated key is: "+secretKey);

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            SecretKey finalKey = new SecretKeySpec(secretKeySpec.getEncoded(), "AES");
            System.out.println("The final derived key is: " +finalKey);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}