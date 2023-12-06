import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_3_Req21 {

    public static void main(String[] argv) throws Exception {
        // Generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(512);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        // Original text
        String originalText = "This is a secret message.";

        // Encryption
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = originalText.getBytes("UTF-8");
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encryptedMessage = new String(encryptedMessageBytes, "UTF-8");

        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decryption
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF-8");

        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}