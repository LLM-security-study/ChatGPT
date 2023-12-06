import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class asym_encryption_3_Req2 {

    private static final String RSA = "RSA";

    public static void main(String... argv) throws Exception {
        
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(1024);
        
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();
        
        final String originalMessage = "Hello, World!";
        System.out.println("Original Message: " + originalMessage);
        
        final byte[] cipherText = encrypt(originalMessage, publicKey);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(cipherText));
        
        final String decryptedMessage = decrypt(cipherText, privateKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        final Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        return cipher.doFinal(message.getBytes());
    }

    private static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception{
        final Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return new String(cipher.doFinal(cipherText));
    }
}