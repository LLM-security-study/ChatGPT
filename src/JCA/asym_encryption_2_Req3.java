import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req3 {
    private static final String ALGORITHM = "RSA";
    
    // Function to create RSA keys
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Function to encrypt using RSA public key
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    // Function to decrypt using RSA private key
    public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(cipherText));
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            String message = "This is a secret message";

            // Encrypt the message
            byte[] cipherText = encrypt(message, keyPair.getPublic());
            System.out.println("Encrypted Message: " + new String(cipherText));

            // Decrypt the message
            String decryptedMessage = decrypt(cipherText, keyPair.getPrivate());
            System.out.println("Deciphered Message: " + decryptedMessage);
        } catch (Exception e) {
            System.err.println("Error occured: " + e.toString());
        }
    }
}