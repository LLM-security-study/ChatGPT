import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req11 {
    public static void main(String[] args) throws Exception {
        String secretKey = "sampleSecretKey!"; // Symmetric secret key 
        String encryptedData = "FridUJddZFm6U9R4QKlQWg=="; // Your encrypted data

        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] original = cipher.doFinal(decoded);

        String originalString = new String(original);
        System.out.println("Decrypted Data: " + originalString);
    }
}