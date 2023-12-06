import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req15 {
    public static void main(String[] args) {

        try {
            // Generate a Secret key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create a Cipher instance
            Cipher aesCipher = Cipher.getInstance("AES");

            // text to be encrypted
            String plainText = "Data to be Encrypted";

            // Encryption
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] byteDataToEncrypt = plainText.getBytes();
            byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

            String strCipherText = Base64.getEncoder().encodeToString(byteCipherText);
            System.out.println("Cipher Text generated using AES is " + strCipherText);

            // Decryption
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey, aesCipher.getParameters());
            byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
            String strDecryptedText = new String(byteDecryptedText);

            System.out.println("Decrypted Text message is " + strDecryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}