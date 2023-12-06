import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;
import java.util.Scanner;

public class KDF_1_Req22 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        Key key = deriveKey(password);

        System.out.println("Derived key: " + key.toString());
    }

    public static SecretKey deriveKey(String password) {
        try {
            // Generate a static salt. In a real-world scenario, it should be a random salt.
            byte[] salt = new byte[16];
            Arrays.fill(salt, (byte) 5);
            
            int iterationCount = 10000;
            int keyLength = 256;  // SHA-256 outputs 256-bit hashes.

            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            return new SecretKeySpec(keyBytes, "AES");  // Use AES as the symmetric encryption algorithm.
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error deriving key", e);
        }
    }
}