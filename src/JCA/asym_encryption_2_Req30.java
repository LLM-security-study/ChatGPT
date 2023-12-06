import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_2_Req30 {

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Let's create a test message
        String message = "Hello, this is a test message!";

        // Encrypt the message
        byte[] encryptedMessage = encrypt(privateKey, message);
        System.out.println("Encrypted message: " + new String(encryptedMessage));

        // Decrypt the message
        byte[] secretMessage = decrypt(publicKey, encryptedMessage);
        System.out.println("Decrypted message: " + new String(secretMessage));
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}