import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_3_Req1 {
    public static void main(String[] args) {
        try {
            // Generating key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Simple message text
            String plainText = "Hello, World";

            // Encrypting the message
            byte[] cipherText = encrypt(plainText, publicKey);
            System.out.println("Encrypted Text: " + new String(cipherText));

            // Decrypting the message
            String decryptedText = decrypt(cipherText, privateKey);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    private static String decrypt(byte[] text, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(text));
    }
}