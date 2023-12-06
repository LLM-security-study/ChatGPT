import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req15 {
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    private static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }

    private static String decrypt(byte[] bytes, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decryptCipher.doFinal(bytes), "UTF-8");
    }

    public static void main(String... args) throws Exception {
        // Generate the key pair
        KeyPair keyPair = generateKeyPair();

        // Create an RSA message
        String message = "The quick brown fox jumps over the lazy dog.";

        // Encrypt it
        byte[] stringToEncrypt = message.getBytes();
        byte[] encryptedData = encrypt(message, keyPair.getPublic());

        // Decrypt it
        String decryptedMessage = decrypt(encryptedData, keyPair.getPrivate());

        System.out.println("Input: " + message);
        System.out.println("Encrypted: " + new String(encryptedData));
        System.out.println("Decrypted: " + decryptedMessage);
    }
}