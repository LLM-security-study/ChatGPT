import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class KDF_2_Req7 {

    public static void main(String[] args) {
        String password = "MySecretPassworD123";
        String salt = "somesalt"; //In a real-world application, a newly generated salt is recommended for each user
        int iterationCount = 65536; 
        int keyLength = 256;
        
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterationCount, keyLength);
        String hashedPassword = DatatypeConverter.printHexBinary(hashedBytes);
        
        System.out.println(hashedPassword);
    }

    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA256" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            KeySpec keySpec = new PBEKeySpec(password, salt, iterations, keyLength);
            return skf.generateSecret( keySpec).getEncoded();
        }
        catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
}