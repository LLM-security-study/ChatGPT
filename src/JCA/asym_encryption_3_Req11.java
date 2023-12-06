import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_3_Req11 {
    public static void main(String args[]) throws Exception{
    
        // Creating the key pairs
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
    
        // Getting text to encrypt
        String text = "This is the text to be encrypted";
    
        // Encrypting the text
        byte[] cipherText = encrypt(text, keyPair.getPublic());

        // Decrypting the text
        String decryptedText = decrypt(cipherText, keyPair.getPrivate());
    
        System.out.println("Original Text: " + text);
        System.out.println("Encrypted Text: " + new String(cipherText, "UTF8"));
        System.out.println("Decrypted Text: " + decryptedText);
    }
  
    public static byte[] encrypt(String msg, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(msg.getBytes());
    }
  
    public static String decrypt(byte[] msg, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(msg));
    }
}