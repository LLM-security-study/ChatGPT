import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class KDF_2_Req14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the password from which a secret key will be generated: ");
        String password = scanner.nextLine();

        System.out.println("Enter a salt value (recommended size is 8 bytes): ");
        String salt = scanner.nextLine();

        int keyLength = 256;
        int iterationCount = 65536; 
        
        char[] passwordCharArray = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        SecretKey secretKey = generateSecretKey(passwordCharArray, saltBytes, iterationCount, keyLength);

        System.out.println("Secret key has been generated");
        System.out.println("Secret key : " + new String(secretKey.getEncoded()));
    }

    private static SecretKey generateSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) {

        SecretKey secretKey = null;
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            secretKey = skf.generateSecret(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return secretKey;
    }
}