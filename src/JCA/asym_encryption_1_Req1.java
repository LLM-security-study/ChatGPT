import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req1 {
    private static final String ALGORITHM = "RSA";
    
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
      
        return new String(Base64.getEncoder().encode(encryptedBytes));
    }
  
    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }
    
    public static void main (String[] args) throws Exception {
        String plainText = "Hello World!";
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        String encryptedText = encrypt(plainText, publicKey);
        String decryptedText = decrypt(encryptedText, privateKey);

        System.out.println("Original Text: " + plainText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}