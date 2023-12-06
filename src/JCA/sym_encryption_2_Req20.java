import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_2_Req20 {
    public static void main(String[] args) {
        try {
            // Generate a Secret Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create a Cipher by specifying the following parameters
            // a. Algorithm name - here it is AES
            // b. Mode - here it is CBC mode
            // c. Padding - here it is PKCS5 padding
            Cipher aesCipher = Cipher.getInstance("AES");

            // Initialize the Cipher for Encryption
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Our message
            String strDataToEncrypt = "Hello";

            // Convert the Input Text to Bytes
            byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
   
            // Perform the operation
            byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

            // b64 is done differently on Android
            String strCipherText = Base64.getEncoder().encodeToString(byteCipherText);
            System.out.println("Cipher Text generated using AES is " + strCipherText);

            // Initialize the same cipher for decryption
            aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());

            // Decrypt the cipher text
            byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
            String strDecryptedText = new String(byteDecryptedText);

            System.out.println("Decrypted Text message is " + strDecryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}