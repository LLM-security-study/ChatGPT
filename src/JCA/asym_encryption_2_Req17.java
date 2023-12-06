import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;

public class asym_encryption_2_Req17 {

  public static void main(String[] args) throws Exception {
      
    KeyPair keyPair = generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    String originalMessage = "This is a confidential message";
    byte[] originalMessageBytes = originalMessage.getBytes();

    byte[] encryptedMessage = encrypt(originalMessageBytes, publicKey);

    byte[] decryptedMessage = decrypt(encryptedMessage, privateKey);

    System.out.println("Original Message: " + originalMessage);
    System.out.println("Decrypted Message: " + new String(decryptedMessage));
  }

  private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair keyPair = generator.genKeyPair();
    return keyPair;
  }

  private static byte[] encrypt(byte[] data, PublicKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return cipher.doFinal(data);
  }

  private static byte[] decrypt(byte[] data, PrivateKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(data);
  }
  
}