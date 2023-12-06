import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class KDF_3_Req24 {
  
    public static void main(String[] args) {
        // password
        char[] password = "password".toCharArray();
  
        // salt should be securely generated and stored
        byte[] salt = new byte[16];
  
        // Iteration count
        int iterationCount = 65536;
  
        // Key length
        int keyLength = 256;
  
        SecretKeyFactory secretKeyFactory = null;
        byte[] hashedPassword = null;
  
        try {
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec specs = new PBEKeySpec(password, salt, iterationCount, keyLength);
            hashedPassword = secretKeyFactory.generateSecret(specs).getEncoded();
  
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
  
        System.out.println("Derived key (base64) : "+Base64.getEncoder().encodeToString(hashedPassword));
    }
}