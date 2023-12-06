import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_2_Req29 {
    public static void main(String[] args) throws Exception {
        // KeyPairGenerator instance for RSA algorithm
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        // Original message
        String msg = "Hello World!";
        System.out.println("Original Message: " + msg);

        // Encryption
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMsg = encryptCipher.doFinal(msg.getBytes());

        // String representation of encrypted message
        String encryptedString = new String(encryptedMsg);
        System.out.println("Encrypted Message: " + encryptedString);

        // Decryption
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMsg = decryptCipher.doFinal(encryptedMsg);

        // String representation of decrypted message
        String decryptedString = new String(decryptedMsg);
        System.out.println("Decrypted Message: " + decryptedString);
    }
}