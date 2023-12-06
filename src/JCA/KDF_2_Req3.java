// Import required classes
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

public class KDF_2_Req3 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Create scanner object to read input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your password");
        String password = sc.next();

        byte[] salt = new byte[16]; // Considering a 16-byte salt
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128); // PBKDF2 with SHA-1 as the hash, with 65536 iterations and a key length of 128
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("Secret key for your password is : %s%n", enc.encodeToString(hash));
    }
}