import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_3_Req26 {

   public static void main(String[] args) throws Exception {
      // create the keys
      KeyPair keyPair = buildKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // the message to encrypt
      String message = "Hello World!";
      
      // encrypt the message
      byte [] encrypted = encrypt(privateKey, message);                     
      System.out.println(new String(encrypted));  // <<encrypted message>>
      
      // decrypt the message
      byte[] secret = decrypt(publicKey, encrypted);                                 
      System.out.println(new String(secret));     // This will print: Hello World!
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