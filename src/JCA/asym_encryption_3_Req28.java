import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_3_Req28 {

  public static void main(String[] args) throws Exception {

    final String originalMessage = "This is a secret message";

    // Create a new instance of KeyPairGenerator for RSA
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(2048);

    // Generate keys
    KeyPair kp = kpg.generateKeyPair();
    PublicKey publicKey = kp.getPublic();
    PrivateKey privateKey = kp.getPrivate();

    // Encrypt the message
    byte[] encryptedMessage = encrypt(originalMessage, publicKey);
    System.out.println("Encrypted message: " + new String(encryptedMessage, "UTF8"));

    // Decrypt the message
    String decryptedMessage = decrypt(encryptedMessage, privateKey);
    System.out.println("Decrypted message: " + decryptedMessage);
  }

  public static byte[] encrypt(String originalMessage, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedData = cipher.doFinal(originalMessage.getBytes());
    return encryptedData;
  }

  public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return new String(cipher.doFinal(encryptedMessage));
  }

}