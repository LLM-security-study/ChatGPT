import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_1_Req13 {
    
  public static void main(String[] args) throws Exception {
    // Generate key pair
    KeyPair keyPair = buildKeyPair();
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // original message
    String message = "This is a secret message";

    // lets encrypt
    byte[] encrypted = encrypt(privateKey, message);                     
    System.out.println(new String(encrypted));  // <<encrypted message>>  
    
    // now lets decrypt
    byte[] secret = decrypt(pubKey, encrypted);                                 
    System.out.println(new String(secret));     // This is a secret message
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

  public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");  
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    
    return cipher.doFinal(encrypted);
  }
}