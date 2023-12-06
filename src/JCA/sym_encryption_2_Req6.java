import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_2_Req6 {
    
    public static void main(String[] args) throws Exception {

        String plainText = "Hello, World!";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

        // Encode to Base64
        String encodedEncryptedText = Base64.getEncoder().encodeToString(encryptedTextBytes);
        System.out.println("Encrypted Text : " + encodedEncryptedText);
        
        // Decryption
        // Since we are using symmetric algorithm, we use the same key for decryption
        byte[] encryptedTextBytesDec = Base64.getDecoder().decode(encodedEncryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytesDec);
        
        String decryptedText = new String(decryptedTextBytes);
        System.out.println("Decrypted Text : " + decryptedText);
    }
}