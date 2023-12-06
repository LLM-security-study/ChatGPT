import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

public class KDF_1_Req2 {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int SALT_SIZE = 16;
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;

    private static SecretKey deriveKey(char[] password, byte[] salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        return factory.generateSecret(spec);
    }
    
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        char[] password = scanner.nextLine().toCharArray();
        
        byte[] salt = generateSalt();
        SecretKey key = deriveKey(password, salt);
        
        System.out.println("Derived key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}