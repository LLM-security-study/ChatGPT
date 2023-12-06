import java.security.*;
import java.util.Base64;

public class asym_encryption_3_Req12 {

    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        // Generate public and private keys
        KeyPair keyPair = buildKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        // Encrypt the message
        String message = "This is a secret message";
        byte[] encryptedMessage = encrypt(privateKey, message);     

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage)); 
        
        // Decrypt the message
        byte[] secret = decrypt(publicKey, encryptedMessage);                                 
        String decryptedMessage = new String(secret);  
        
        System.out.println("Decrypted Message: " + decryptedMessage);
    }


    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
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

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}