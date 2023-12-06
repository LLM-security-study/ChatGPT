import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_2_Req24 {

    public static void main(String[] args) throws Exception {

        String originalMessage = "Hello World";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        SecretKey secretKey = keyGenerator.generateKey();
        byte[] aesKeyData = secretKey.getEncoded();
        
        SecretKeySpec aesKey = new SecretKeySpec(aesKeyData, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Encryption
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedMessage = cipher.doFinal(originalMessage.getBytes());
        String encryptedMessageStr = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted Message: " + encryptedMessageStr);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedMessageStr));
        String decryptedMessageStr = new String(decryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessageStr);
    }
}