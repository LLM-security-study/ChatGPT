import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req7 {
    public static void main(String[] args) throws Exception {
        String clearText = "Hello, World!";

        // Generate AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(clearText.getBytes());

        // Decode to Base64
        String encoded = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encoded: " + encoded);

        // Decrypt
        byte[] decoded = Base64.getDecoder().decode(encoded);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(decoded);

        System.out.println("Decrypted: " + new String(decrypted));
    }
}