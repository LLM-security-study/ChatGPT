import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req21 {
  public static void main(String[] args) throws Exception {
    // Generate KeyPair.
    KeyPair keyPair = generateKey();
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();
    
    String plainText = "Hello, World!";

    // Encryption.
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    System.out.println("Encrypted text: "+new String(encryptedBytes));

    // Decryption.
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted text: "+decryptedText);

  }
  
  public static KeyPair generateKey() throws NoSuchAlgorithmException {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048); // You can use different sizes such as 2048, 4096
    KeyPair pair = keyGen.generateKeyPair();
    return pair;
  }
}