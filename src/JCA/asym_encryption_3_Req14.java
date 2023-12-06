import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_3_Req14 {
  public static void main(String[] args) throws Exception {
    // Create public and private keys
    KeyPair keyPair = buildKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    
    // Encrypt the message
    String message = "This is a secret message";
    byte[] encryptedMessage = encrypt(publicKey, message); 

    // Decrypt the message
    String decryptedMessage = decrypt(privateKey, encryptedMessage);                                   

    System.out.println(decryptedMessage); 
  }

  public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
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

  public static String decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");  
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
    return new String(cipher.doFinal(encrypted));
  }
}