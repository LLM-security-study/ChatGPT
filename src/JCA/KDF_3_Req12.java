import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class KDF_3_Req12 {
    public static void main(String []args){
        String password = "YourPassword"; //store your password here
        byte[] salt = new byte[16]; // Substitute this salt with real random salt
        int iterationCount = 10000; 
        int keyLength = 128; //key length

        SecretKeyFactory factory;
        byte[] hash = null;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            //Handle the exception
            e.printStackTrace();
        }

        //print key 
        System.out.println(bytesToHex(hash));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}