import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_3_Req3 {

    public static void main(String[] args) throws Exception {
        // The original message
        String originalMessage = "Hello, World!";

        // Generate the RSA key pair
        KeyPair keyPair = generateRSAKeyPair();

        // Get the public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt the original message
        byte[] encryptedMessage = encrypt(originalMessage, publicKey);

        // Decrypt the encrypted message
        String decryptedMessage = decrypt(encryptedMessage, privateKey);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + new String(encryptedMessage));
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedMessage));
    }
}