import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_3_Req9 {
  public static void main(String[] args) {
    try {
      // generate public and private keys
      KeyPair keyPair = buildKeyPair();
      PublicKey pubKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // encrypt the message
      byte[] encrypted = encrypt(privateKey, "This is a secret message");
      System.out.println("Encrypted message: "+ new String(encrypted, "UTF8")); 
      
      // decrypt the message
      byte[] decrypted = decrypt(pubKey, encrypted);                                 
      System.out.println("Decrypted message: "+ new String(decrypted, "UTF8"));
    } catch(Exception e) {
      e.printStackTrace();
    }
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