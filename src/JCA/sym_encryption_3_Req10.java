import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req10 {
    public static void main(String[] args) {
        try {
            // Define the original secret key
            byte[] key = "ThisIsASecretKey".getBytes("UTF-8");
            Key aesKey = new SecretKeySpec(key, "AES");

            // Create an AES Cipher instance
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Sample encrypted text for decryption
            String encryptedText = "ABcdeFghIlmnopQRstuvWXyz12345678==";
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encryptedBytes));
            System.out.println("Decrypted text: " + decrypted);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}