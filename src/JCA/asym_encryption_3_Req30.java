import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_3_Req30 {

    public static final String RSA = "RSA";

    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Let's encrypt
        String message = "This is the secret message!";
        byte[] encryptedMessage = encrypt(privateKey, message);    

        // Now let's decrypt
        byte[] secretMessage = decrypt(publicKey, encryptedMessage);                                 

        System.out.println(new String(secretMessage));    // Should print out 'This is the secret message!'
    }

    public static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }

    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }
}