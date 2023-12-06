import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class KDF_2_Req10 {
    public static SecretKey getSecretKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, 1000, 256);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return secretKeyFactory.generateSecret(pbeKeySpec);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        // Normally, you should use a randomly generated salt.
        // For demo purposes, let's use a constant salt.
        byte[] salt = new byte[16];

        try {
            SecretKey secretKey = getSecretKey(password.toCharArray(), salt);
            System.out.println("Secret Key: " + secretKey.toString());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
           System.err.println("Error generating secret key: " + e.getMessage());
        }
    }
}