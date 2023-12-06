import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_1_Req29 {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.generateKeyPair();

        String originalMessage = "Hello World!";

        Cipher cipher = Cipher.getInstance("RSA");

        // Encryption
        PublicKey publicKey = keyPair.getPublic();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(originalMessage.getBytes());
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decryption
        PrivateKey privateKey = keyPair.getPrivate();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage = new String(decryptedBytes);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}