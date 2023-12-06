import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class KDF_1_Req26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter a password for key derivation:");
        String password = scanner.nextLine();
        
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            //change these as needed
            final int iterationCount = 65536;  // number of iterations for the algorithm
            final int keyLength = 256;  // key length in bits

            //salt should be securely randomized
            byte[] salt = new byte[16]; //salt size depends on the algorithm used, 16 bytes is usually secure

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKey key = factory.generateSecret(spec);
            
            System.out.println("Generated cryptographic key: " + new String(key.getEncoded()));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}