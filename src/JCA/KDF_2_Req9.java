import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_2_Req9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        SecretKey secretKey = createSecretKey(password);
        System.out.println("Your secret key: " + secretKey.toString());
    }

    protected static SecretKey createSecretKey(String password){
        try {
            byte[] key = (password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            return secretKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}