import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req6 {
    public static void main(String[] args) throws Exception {
        String plainText = "Hello, World!";

        // Generate a RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt the message
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = plainText.getBytes("UTF-8");
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encryptedMessage = new String(encryptedMessageBytes, "UTF-8");

        // Display the encrypted message
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decrypt the message
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF-8");

        // Display the decrypted message
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}