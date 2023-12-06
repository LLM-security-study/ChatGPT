import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_1_Req27 {
    public static void main(String[] args) {
        String password = "userPassword";  
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        int iterationCount = 65536;  // number of hash function iterations
        int keyLength = 128;  // output hash length

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = keyFactory.generateSecret(keySpec).getEncoded();

            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(hash));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}