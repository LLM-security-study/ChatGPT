import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_3_Req16 {

    public static void main(String[] args) {

        try {
            // Generate a secret key for AES encryption
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128); // AES key size is 128 [or 192, 256]
            SecretKey secretKey = generator.generateKey();

            // Get Cipher instance and initiate in encrypt mode
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the message
            String plainText = "This is a test message";
            byte[] cipherText = cipher.doFinal(plainText.getBytes());

            System.out.println("Encrypted Message: "+ Base64.getEncoder().encodeToString(cipherText));

            // Reinitialize the cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decrypt the message
            byte[] decryptedText = cipher.doFinal(cipherText);

            System.out.println("Decrypted Message: "+ new String(decryptedText));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}