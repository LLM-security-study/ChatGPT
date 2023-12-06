import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_2_Req28 {
    public static void main(String[] args) throws Exception {
        String plaintext = "Hello, world!";

        // Generate a key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // Get a cipher instance
        Cipher cipher = Cipher.getInstance("AES");

        // Encrypt the plain text
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        System.out.println("Encrypted text: " + Base64.getEncoder().encodeToString(encrypted));

        // Decrypt the encrypted text
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted text: " + new String(decrypted));
    }
}