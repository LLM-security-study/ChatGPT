import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req6 {
    public static void main(String[] args) {
        String secretKey = "mySuperSecretKey"; // Replace this with your key
        String encryptedData = "jRpfvOydk3L40v0YchRmiA=="; // Replace with your encrypted string

        try {
            System.out.println("Decrypted data: " + decrypt(secretKey, encryptedData));
        } catch (Exception e) {
            System.err.println("Error while decrypting: " + e.getMessage());
        }
    }

    public static String decrypt(String secret, String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}