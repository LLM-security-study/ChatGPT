import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class sym_encryption_2_Req23 {
    public static void main(String[] args) throws Exception {
        String text = "This is a test text";

        // Generate a new AES secret key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);  // Key size
        SecretKey secretKey = keygen.generateKey();

        // Encrypt data
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypted data: " + Arrays.toString(encryptedData));

        // Decrypt data
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        System.out.println("Decrypted data: " + new String(decryptedData, StandardCharsets.UTF_8));
    }
}