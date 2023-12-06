import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class KDF_2_Req2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Create SHA-256 hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] salt = md.digest(password.getBytes());

            // Key derivation with PBKDF2
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128); // 128 bit key
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            
            // print the generated secret key
            System.out.println("Generated Secret Key: " + bytesToHex(hash));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Function to convert byte array to Hexadecimal
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}