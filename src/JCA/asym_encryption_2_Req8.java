import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req8 {
  
  public static void main(String[] args) throws Exception {
    
    // Generate public and private keys
    KeyPair keyPair = buildKeyPair();
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // Encrypt the message
    byte [] encrypted = encrypt(pubKey, "This is a secret message");
    System.out.println("Encrypted message: " + new String(encrypted));
        
    // Decrypt the message
    byte[] secret = decrypt(privateKey, encrypted);
    System.out.println("Decrypted message: " + new String(secret));
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
    
  public static byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");  
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
    return cipher.doFinal(encrypted);
  }

}