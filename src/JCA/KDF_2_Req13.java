import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.xml.bind.DatatypeConverter;
import java.util.Scanner; 

public class KDF_2_Req13 {
   
    public static SecretKey generateSecretKey (char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, "AES");
    }

    public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password to generate Secret Key: ");
        char[] password = scanner.nextLine().toCharArray();
        
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[20];
        sr.nextBytes(salt);
        
        SecretKey secretKey = generateSecretKey(password, salt);
        System.out.println("Secret Key Generated: " + DatatypeConverter.printHexBinary(secretKey.getEncoded()));
    }
}