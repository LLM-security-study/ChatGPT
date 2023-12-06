import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_3_Req13 {
    public static void main(String[] args) {
        try {
            String encryptedText = "Replace this with encrypted text";
            String key = "Replace this with key_16_chars";

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            String decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
            System.out.println("Decrypted text: " + decryptedText);

        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
    }
}