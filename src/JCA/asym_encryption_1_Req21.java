import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_1_Req21 {

    public static void main (String args[]) throws Exception {
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String message = "Hello, World!";
        
        byte[] encrypted = encrypt(privateKey, message);     
        System.out.println(new String(encrypted));

        byte[] secret = decrypt(pubKey, encrypted);                                 
        System.out.println(new String(secret));     
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

    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}