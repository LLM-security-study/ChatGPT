import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req15 {
    public static void main(String[] args) {
        try {
            // Generate a symmetric key using KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();

            // Encrypt the text
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedText = cipher.doFinal("Hello, World!".getBytes());

            // Print encrypted text
            String encodedEncryptedText = Base64.getEncoder().encodeToString(encryptedText);
            System.out.println("Encrypted Text: " + encodedEncryptedText);
            
            //Now decrypt the text
            //Note: In the real world scenario we will get key in Bytes. Hence we convert it into bytes
            byte[] decodedKey = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(key.getEncoded()));
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] decryptedText = decipher.doFinal(encryptedText);

            System.out.println("Decrypted Text: " + new String(decryptedText));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}