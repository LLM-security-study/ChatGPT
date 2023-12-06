import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;

public class asym_encryption_2_Req20 {

    public static void main(String[] args) throws Exception {
        final String originalMessage = "This is a secret message";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String encryptedMessage = encrypt(originalMessage, keyPair.getPublic());
        String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());
        
        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static String encrypt(String message, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherTextBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherTextBytes);
    }

    private static String decrypt(String encryptedMessage, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}