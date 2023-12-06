import java.security.*;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KDF_2_Req19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your password:");

        String password = scanner.nextLine();
        SecretKey secretKey = generateSecretKey(password);

        System.out.println("The generated secret key is:");
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }

    public static SecretKey generateSecretKey(String password) {
        try {
            byte[] passwordInBytes = password.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");

            byte[] key = sha.digest(passwordInBytes);
            key = Arrays.copyOf(key, 16); // AES keys should be of length 16

            return new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            System.out.println("Error while generating the secret key: " + e.toString());
            return null;
        }
    }
}