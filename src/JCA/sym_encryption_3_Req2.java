import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class sym_encryption_3_Req2 {

    public static void main(String[] args) throws  Exception {
        String plainText = "Hello, World!";

        // Generate a key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Create Cipher Instance
        Cipher aesCipher = Cipher.getInstance("AES");

        // Initialize Cipher
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the data
        byte[] byteDataToEncrypt = plainText.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

        // Initialize the same cipher for decryption
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the data
        byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
        String decryptedText = new String(byteDecryptedText, StandardCharsets.UTF_8);

        // Print the decrypted text
        System.out.println(decryptedText);
    }
}