import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class asym_encryption_1_Req12 {
    // Variables holding the keys
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        // Initializing the keys
        initialize();

        String text = "Hello, World!";

        // Encryption
        byte[] cipherText = encrypt(text, publicKey);

        // Decryption
        String decryptedText = decrypt(cipherText, privateKey);

        System.out.println("Decrypted Text After Decryption: " + decryptedText);
    }

    private static void initialize() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
    }
}