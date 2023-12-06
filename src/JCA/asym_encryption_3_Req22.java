import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class asym_encryption_3_Req22 {
    public static void main(String[] args) throws Exception {
        // Generate an RSA keypair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048); // You can use a different value here
        KeyPair kp = kpg.generateKeyPair();

        // The original message
        String message = "Hello, World!";

        // Encrypt the message
        String encryptedMessage = encrypt(message, kp.getPublic());

        // Display the encrypted message
        System.out.println(encryptedMessage);
    }

    public static String encrypt(String plainText, PublicKey publicKey ) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

}