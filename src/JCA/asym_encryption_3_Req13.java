import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_3_Req13 {
    public static void main(String[] args) throws Exception {

        // Generate public and private keys
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Original message
        String originalMessage = "This is the original message";

        // Encrypt message
        Cipher enCipher = Cipher.getInstance("RSA");
        enCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = enCipher.doFinal(originalMessage.getBytes());

        // Print encrypted message
        System.out.println("Encrypted message: " + new String(Base64.getEncoder().encode(encryptedMessage), StandardCharsets.UTF_8));

        // Decrypt message
        Cipher deCipher = Cipher.getInstance("RSA");
        deCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = deCipher.doFinal(encryptedMessage);

        // Print decrypted message
        System.out.println("Decrypted message: " + new String(decryptedMessage, StandardCharsets.UTF_8));
    }
}