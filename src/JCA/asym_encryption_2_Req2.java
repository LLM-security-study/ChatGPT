import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_2_Req2 {

    public static byte[] encrypt(String plainText, PublicKey publicKey ) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }
    
    public static String decrypt(byte[] encryptedText, PrivateKey privateKey) throws Exception {
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return new String(decriptCipher.doFinal(encryptedText), "UTF-8");
    }
    
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        
        KeyPair keyPair = kpg.generateKeyPair();
        String plainText = "Hello RSA";
        
        byte[] encryptedText = encrypt(plainText, keyPair.getPublic());
        String decryptedText = decrypt(encryptedText, keyPair.getPrivate());
        
        System.out.println("Original Text:" + plainText);
        System.out.println("Encrypted Text:" + new String(encryptedText));
        System.out.println("Decrypted Text:" + decryptedText);
    }
}