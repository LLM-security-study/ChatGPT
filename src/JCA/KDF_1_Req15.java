import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.util.*;

public class KDF_1_Req15 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Password: ");
        char[] password = scan.nextLine().toCharArray();

        /* 
          * Note: Always use SecureRandom instead of just Random
          * SecureRandom is safe for use in cryptography.
        */
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];  
        random.nextBytes(salt);

        // Iterations and key length can be adjusted based on security requirement.
        int iterations = 10000;  
        int keyLength = 256;  // Our cryptographic key will be of length 256 bits

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();

        System.out.println("Derived Key : " + Arrays.toString(hash));    
    }
}