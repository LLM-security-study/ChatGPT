import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class KDF_3_Req1 {
    public static void main(String[] args) throws Exception {
        String password = "password"; // example
        int iterationCount = 65536;
        int keyLength = 256;
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] secretKey = factory.generateSecret(keySpec).getEncoded();
        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Secret Key: " + Base64.getEncoder().encodeToString(secretKey));
    }
}