import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_1_Req24 {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        String plainText = "Hello, world!";
        
        byte[] encryptedText = encrypt(publicKey, plainText);
        System.out.println("Encrypted Text: " + new String(encryptedText));
        
        String decryptedText = decrypt(privateKey, encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
   
    public static byte[] encrypt(PublicKey key, String text) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }
   
    public static String decrypt(PrivateKey key, byte[] text) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(text));
    }
}