import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;

public class KDF_2_Req29 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "Your Password"; //replace with user provided password
        byte[] salt = new byte[16]; //replace with a secure randomly generated salt

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256); // AES-256
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        System.out.println("Generated Secret Key : " + bytesToHex(secret.getEncoded()));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}