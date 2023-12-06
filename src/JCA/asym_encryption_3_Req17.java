import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_3_Req17 {
    public static void main(String args[]) {
        try {
            // Generate a public and private key pair
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();

            // Print the generated keys
            System.out.println("Generated Key Pair");
            System.out.println("------------------");
            System.out.println("Public Key: "+publicKey);
            System.out.println("Private key: "+privateKey);

            // Create a message to encrypt
            String originalMessage = "Text to encrypt";

            // Encrypt the message
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] secretMessageBytes = originalMessage.getBytes();
            byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
            String encryptedMessage = new String(encryptedMessageBytes);

            // Print the encrypted message
            System.out.println("Encrypted Message");
            System.out.println("-----------------");
            System.out.println(encryptedMessage);

            // Decrypt the message
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
            String decryptedMessage = new String(decryptedMessageBytes);

            // Print the decrypted message
            System.out.println("Decrypted Message");
            System.out.println("-----------------");
            System.out.println(decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}