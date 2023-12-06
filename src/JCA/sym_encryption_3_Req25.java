import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req25 {

    public static void main(String[] args) throws Exception {
        // The data to be decrypted
        String encryptedData = "Base64 encrypted string here";

        // The key used for encryption
        String encryptionKey = "Your Encryption Key Here";

        // Decrypt the data
        String decryptedData = decrypt(encryptedData, encryptionKey);

        System.out.println("Decrypted data: " + decryptedData);
    }

    public static String decrypt(String encryptedData, String encryptionKey) throws Exception {
        // Generate key
        SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");

        // Initialize the Cipher for decryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(decryptedData);
    }
}