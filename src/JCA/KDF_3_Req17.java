import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class KDF_3_Req17 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String originalPassword = "password123";
        String generatedSecuredPasswordHash = setPassword(originalPassword);
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = validatePassword("password123", generatedSecuredPasswordHash);
        System.out.println(matched);
    }

    private static String setPassword(String password) 
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = new byte[16];
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    private static boolean validatePassword(String originalPassword, String storedPassword) 
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        String generatedSecuredPasswordHash = setPassword(originalPassword);
        return storedPassword.equalsIgnoreCase(generatedSecuredPasswordHash);
    }
}