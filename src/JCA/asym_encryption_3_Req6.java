import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_3_Req6 {

    public static void main(String[] args) throws Exception {
        // Generate key pair for 1024-bit RSA encryption and decryption
        KeyPair kp = generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        String plainText = "This is a secret message";

        // Encrypt the plaintext using the public key
        System.out.println("\nStart of Encryption");
        byte[] cipherText = encrypt(publicKey, plainText);
        System.out.println("Encrypted Text: " + new String(cipherText));

        // Decrypt the ciphertext using the private key
        System.out.println("\nStart of Decryption");
        String decryptedText = decrypt(privateKey, cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(encrypted);

        return new String(decryptedBytes);
    }
}