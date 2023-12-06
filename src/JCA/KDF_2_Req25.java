import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.SecretKey;

public class KDF_2_Req25 {
    public static void main(String[] args) {
        String password = "UserPassword";  // Replace with the user's password

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //Create the secret key
            SecretKey secretKey = new SecretKeySpec(bytes, "AES");

            //Print the secret key
            System.out.println(Arrays.toString(secretKey.getEncoded()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}