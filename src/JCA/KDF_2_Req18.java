import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class KDF_2_Req18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.next();
        
        byte[] salt = new byte[16]; // preferably, this should be stored securely

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey key = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(key.getEncoded(), "AES");
            
            System.out.println("Secret Key: " + bytesToHex(secretKey.getEncoded()));
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}