import java.security.KeyPair;
import java.util.Base64;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class asym_encryption_1_Req26 {

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); 

        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    public static String encrypt(String plainText, KeyPair keyPair) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

        byte[] secretMessageBytes = plainText.getBytes();
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

        return encryptedMessage;
    }

    public static String decrypt(String encryptedMessage, KeyPair keyPair) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] decryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] originalMessageBytes = decryptCipher.doFinal(decryptedMessageBytes);
        String originalMessage = new String(originalMessageBytes);

        return originalMessage;
    }

    public static void main (String args[]) throws Exception {
        KeyPair kp = generateKeyPair();
        String encrypted = encrypt("Hello World!", kp);
        String decrypted = decrypt(encrypted, kp);
        System.out.println("Encrypted = " + encrypted);
        System.out.println("Decrypted = " + decrypted);
     }
}