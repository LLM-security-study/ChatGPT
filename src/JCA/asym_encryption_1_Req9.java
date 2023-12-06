import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req9 {
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String originalString = "Hello, world!";

        // Encryption
        byte[] cipherText = encrypt(originalString, keyPair.getPublic());
        System.out.println("Encrypted Text: " + new String(cipherText));

        // Decryption
        String decryptedText = decrypt(cipherText, keyPair.getPrivate());
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static byte[] encrypt(String original, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(original.getBytes());
    }

    public static String decrypt(byte[] encrypted, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encrypted);
        return new String(decryptedBytes);
    }
}