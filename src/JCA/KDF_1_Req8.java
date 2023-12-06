import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class KDF_1_Req8 {
    private static final String PASSWORD = "UserPassword";

    public static void main(String[] args) {
        try {
            // Create PBEKeySpec
            PBEKeySpec keySpec = new PBEKeySpec(PASSWORD.toCharArray());

            // Create secret key factory
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            // Create a secret key
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            System.out.println("Generated Secret Key : " + bytesToHex(secretKey.getEncoded()));
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}