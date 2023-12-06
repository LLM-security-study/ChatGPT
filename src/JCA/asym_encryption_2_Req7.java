import java.security.*;
import javax.crypto.*;

public class asym_encryption_2_Req7 {
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }
    
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] secretMessageBytes = plainText.getBytes("UTF-8");
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

        return encryptedMessageBytes;
    }
    
    public static String decrypt(byte[] encryptedMessageBytes, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, "UTF-8");

        return decryptedMessage;
    }
    
    public static void main(String args[]) throws Exception {
        KeyPair pair = generateKeyPair();
        String testMessage = "This is the message to be encrypted";
        
        byte[] encryptedMessageBytes = encrypt(testMessage, pair.getPublic());

        String decryptedMessage = decrypt(encryptedMessageBytes, pair.getPrivate());
        
        System.out.println("Original Message: " + testMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}