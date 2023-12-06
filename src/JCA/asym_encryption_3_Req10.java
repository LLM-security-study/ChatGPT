import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_3_Req10 {
    public static void main(String[] args) throws Exception {
        // The original message
        String originalMessage = "Hello, this is the secret message";
        System.out.println("Original message: " + originalMessage);

        // Generate a new key pair
        KeyPair kp = generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        // Now we encrypt the message using the public key
        byte[] encryptedMessage = encrypt(publicKey, originalMessage);
        System.out.println("Encrypted message: " + new String(encryptedMessage));

        // Decrypt the message using the private key
        String decryptedMessage = decrypt(privateKey, encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    // Typically, you should store the key pair in a secure location
    // and read them back when you want to use them, instead of
    // generating a new pair each time. But for simplicity, this
    // sample code will simply generate a new pair each run.
    static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    static String decrypt(PrivateKey privateKey, byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(encryptedMessage);
        return new String(result, "UTF-8");
    }
}