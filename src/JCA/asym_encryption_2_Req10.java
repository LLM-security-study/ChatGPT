import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_2_Req10 {
    public static void main(String[] args) {
        try {
            // Generate key pair
            KeyPair keyPair = generateKeyPair();
            
            // Get public and private keys
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            // Original text
            String plainText = "Hello, World!";
            System.out.println("Original Text: " + plainText);
            
            // Encrypt text
            byte[] cipherText = encrypt(plainText, privateKey);
            System.out.println("Encrypted Text: " + new String(cipherText, "UTF8"));
            
            // Decrypt text
            String decryptedText = decrypt(cipherText, publicKey);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Key pair generation
    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }
    
    // Encryption method
    private static byte[] encrypt(String plainText, PrivateKey privateKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));
        return cipherText;
    }
    
    // Decryption method
    private static String decrypt(byte[] cipherText, PublicKey publicKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedBytes = decryptCipher.doFinal(cipherText);
        String decrypted = new String(decryptedBytes, "UTF8");
        return decrypted;
    }
}