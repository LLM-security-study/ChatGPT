import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class KDF_2_Req26 {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String salt = "12345678"; // This should be securely generated and stored
        
        String secretKey = generateSecretKey(password, salt);

        System.out.println("The generated secret key is: " + secretKey);
    }

    private static String generateSecretKey(String password, String salt) {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(passwordChars, Character.MIN_VALUE);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] secretKey = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(secretKey);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generating key from password", e);
        } finally {
            spec.clearPassword();
        }
    }
}