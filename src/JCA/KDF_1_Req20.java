import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class KDF_1_Req20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password: ");

        // It's recommended to use SecureRandom in a real application
        byte[] salt = new byte[16]; 
        Arrays.fill(salt, (byte) 0x00); 

        KeySpec spec = new PBEKeySpec(scanner.nextLine().toCharArray(), salt, 65536, 128); 
        SecretKeyFactory factory = null;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded(); 
            System.out.println("Derived Key: " + DatatypeConverter.printHexBinary(hash)); 
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}