import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_3_Req16 {
    public static void main (String[] args) throws Exception {
        // Generate Public and Private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt the message
        String message = "Hello World!";
        byte[] encrypted = encrypt(publicKey, message);
        System.out.println(new String(Base64.getEncoder().encode(encrypted)));

        // Decrypt the message
        byte[] secret = decrypt(privateKey, encrypted);
        System.out.println(new String(secret)); // This line will print out "Hello World!"
    }

    public static KeyPair buildKeyPair() throws Exception {
       final int keySize = 2048;
       KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
       keyPairGenerator.initialize(keySize);      
       return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        return cipher.doFinal(message.getBytes());  
    }

    public static byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return cipher.doFinal(encrypted);
    }
}