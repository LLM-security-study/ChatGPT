import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req24 {
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
		
        // Input message
        String message = "Hello, world!"; 
		
        // Encrypt the message
        byte[] encryptedMessage = encrypt(privateKey, message);
        System.out.println(new String(encryptedMessage)); // print encrypted message

        // Decrypt the message
        String decryptedMessage = decrypt(publicKey, encryptedMessage);
        System.out.println(decryptedMessage);  // print decrypted message
    }

    public static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    public static String decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        byte[] decryptedBytes = cipher.doFinal(encrypted);
        return new String(decryptedBytes);
    }
}