import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_3_Req8 {
    public static void main(String[] args) throws Exception {
        // Generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Our secret message
        String message = "This is a secret message";

        // Let's encrypt
        byte[] encrypted = encrypt(privateKey, message);     

        // Now let's decrypt
        String decrypted = decrypt(pubKey, encrypted);       

        System.out.println(decrypted);  // This is a secret message
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 512;
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