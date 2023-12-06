// KDF_3_Req19.java

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

class KDF_3_Req19 {
    public static void main(String[] args) {
        String password = "password123";
        byte[] salt = new byte[16];
        
        int iterationCount = 10000;
        int keyLength = 256;
        
        SecretKeyFactory factory;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

}