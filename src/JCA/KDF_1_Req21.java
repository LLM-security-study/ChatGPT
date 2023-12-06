import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.*;

public class KDF_1_Req21 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        // Generate salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Derive key using PBKDF2WithHmacSHA256 algorithm
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256); 
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        System.out.println("Derived cryptographic key: " + Arrays.toString(hash));
    }
}