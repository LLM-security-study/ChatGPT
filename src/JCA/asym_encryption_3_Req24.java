import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;
import java.util.Base64;
import java.security.PublicKey;
import java.security.PrivateKey;

public class asym_encryption_3_Req24 {

    // Size of key we want to generate
    private static final int keySize = 2048;

    public static void main(String[] args) throws Exception {
 
        // Text that we want to encrypt
        String originalText = "This is the text we want to encrypt.";

        // Generate public and private keys
        KeyPair keyPair = generateKeyPair(keySize);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt the original text with public key
        byte[] cipherText = encrypt(originalText, publicKey);
        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(cipherText));

        // Decrypt the text with private key
        String decryptedText = decrypt(cipherText, privateKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static KeyPair generateKeyPair(int keySize) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keySize);
        return generator.generateKeyPair();
    }

    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, PrivateKey privateKey ) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decryptCipher.doFinal(cipherText), "UTF-8");
    }
}