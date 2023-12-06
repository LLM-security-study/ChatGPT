import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class sym_encryption_1_Req17 {

    public static void main(String[] args) throws Exception {
        // Generate a secret key (AES)
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(new SecureRandom());
        SecretKey secretKey = generator.generateKey();
        
        String originalMessage = "Hello, World";

        // Encrypt the message
        String encryptedMessage = encryptWithAESKey(originalMessage, secretKey);
        System.out.println("Encrypted: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decryptWithAESKey(encryptedMessage, secretKey);
        System.out.println("Decrypted: " + decryptedMessage);
    }

    public static String encryptWithAESKey(String msg, SecretKey secretKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteCipherText = aesCipher.doFinal(msg.getBytes());
        return new String(byteCipherText);
    }

    public static String decryptWithAESKey(String msg, SecretKey secretKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        byte[] byteEncryptedText = msg.getBytes();
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytePlainText = aesCipher.doFinal(byteEncryptedText);
        return new String(bytePlainText);
    }
}