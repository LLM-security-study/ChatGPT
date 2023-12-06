import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;

public class sym_encryption_2_Req1 {
    public static void main(String[] args) throws Exception {
        // Generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES");

        String plainText = "Hello, World!";

        // Encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] plainTextByte = plainText.getBytes();
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        String encryptedText = DatatypeConverter.printBase64Binary(encryptedByte);
        System.out.println("Encrypted Text : " + encryptedText);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedTextByte = DatatypeConverter.parseBase64Binary(encryptedText);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        System.out.println("Decrypted Text : " + decryptedText);
    }
}