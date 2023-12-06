import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req19 {
    public static void main(String[] args) throws Exception {
        // Generate key pair 
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
    
        // Encrypt the plaintext 
        String message = "Plaintext message to be encrypted";
        byte[] encrypted = encrypt(privateKey, message);
        System.out.println(new String(encrypted));  // print ciphertext
    
        // Decrypt ciphertext
        byte[] secret = decrypt(publicKey, encrypted);       
        System.out.println(new String(secret));     // print plaintext
    }
    
    private static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    private static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    private static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}