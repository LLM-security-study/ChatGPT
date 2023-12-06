import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_1_Req11 {
    public static void main(String[] args) {
        try {
            final String originalMessage = "This is a secret message";

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair pair = keyGen.generateKeyPair();
            PublicKey pubKey = pair.getPublic();
            PrivateKey privateKey = pair.getPrivate();

            String encryptedMessage = encrypt(originalMessage, pubKey);
            System.out.println("Original Message: " + originalMessage);
            System.out.println("Encrypted Message: " + encryptedMessage);

            String decryptedMessage = decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //function to encrypt a string
    public static String encrypt(String msg, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new String(cipher.doFinal(msg.getBytes()));
    }
    //function to decrypt a string
    public static String decrypt(String msg, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(msg.getBytes()));
    }
}