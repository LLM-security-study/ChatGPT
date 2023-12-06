import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_2_Req13 {
    public static void main(String[] args) throws Exception {
        String plainText = "Hello, World!";
        
        // Generate public and private keys.
        KeyPair kp = buildKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        // Encrypt the plain text using the public key.
        byte[] cipherText = encrypt(publicKey, plainText);
        System.out.println("Cipher Text: " + new String(cipherText));
        
        // Decrypt the cipher text using the private key.
        String decryptedPlainText = decrypt(privateKey, cipherText);
        System.out.println("Decrypted Plain Text: " + decryptedPlainText);
    }

    public static KeyPair buildKeyPair() throws Exception {
        int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }
    
    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    public static String decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return new String(cipher.doFinal(encrypted));
    }
}