import java.security.*;
import javax.crypto.*;

public class asym_encryption_1_Req3
{
    private static final String ALGORITHM = "RSA";
    
    public static void main(String[] argv) throws Exception 
    {
        // Generate keys
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String originalText = "This is a secure message";
        System.out.println("Original Text: " + originalText);

        String cipherText = encrypt(originalText, publicKey);
        System.out.println("Encrypted Text: " + cipherText);
        
        String decryptedText = decrypt(cipherText, privateKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }
    
    static KeyPair generateKeyPair() throws NoSuchAlgorithmException 
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    static String encrypt(String plaintext, Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return new String(encryptedBytes, "UTF8");
    }

    static String decrypt(String ciphertext, Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(ciphertext.getBytes("UTF8"));
        return new String(decryptedBytes);
    }
}