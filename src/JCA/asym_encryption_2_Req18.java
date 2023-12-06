import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_2_Req18 {
    
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String originalMessage = "This is a confidential message";
        
        // Encrypt the message
        byte[] encryptedMessage = encryptRSA(publicKey, originalMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + new String(encryptedMessage));

        // Decrypt the message
        String decryptedMessage = decryptRSA(privateKey, encryptedMessage);

        System.out.println("Decrypted Message: " + decryptedMessage);
    }
    
    public static byte[] encryptRSA(PublicKey publicKey, String message) throws Exception {
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        return rsaCipher.doFinal(message.getBytes());
    }

    public static String decryptRSA(PrivateKey privateKey, byte[] encryptedMessage) throws Exception {
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] result = rsaCipher.doFinal(encryptedMessage);
        
        return new String(result);
    }

}