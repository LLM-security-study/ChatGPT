import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req20 {
    private static final String RSA = "RSA";

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
        generator.initialize(2048, new SecureRandom());
        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }

    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(RSA);
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance(RSA);
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedTextBytes = decryptCipher.doFinal(cipherText);
        return new String(decryptedTextBytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        String message = "Hello, World!";

        // Encryption
        byte[] cipherText = encrypt(message, keyPair.getPublic());

        // Decryption
        String decryptedMessage = decrypt(cipherText, keyPair.getPrivate());

        System.out.println("Original Message: " + message);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
