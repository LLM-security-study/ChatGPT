import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_2_Req4 {
  
  public static void main(String[] args) throws Exception {
  
    // Generate key pair
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    // String to be encrypted
    String plainText = "Hello, World!";

    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // Encrypt the string using the public key
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] secretMessageBytes = plainText.getBytes("UTF-8");
    byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
    String encryptedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

    System.out.println("Encrypted message: " + encryptedMessage);

    // Decrypt the message using the private key
    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
    String decryptedMessage = new String(decryptedMessageBytes, "UTF-8");

    System.out.println("Decrypted message: " + decryptedMessage);
  }
}