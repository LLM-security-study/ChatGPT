import java.security.*;
import javax.crypto.*;

public class asym_encryption_3_Req25 {
  public static void main(String[] args) throws Exception {
    // Check if provider supports RSA
    if (Cipher.getMaxAllowedKeyLength("RSA") <= 0)
      throw new Exception("Unsupported RSA");

    // Generate key pair
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(1024);
    KeyPair kp = kpg.genKeyPair();
    PublicKey publicKey = kp.getPublic();
    PrivateKey privateKey = kp.getPrivate();

    // Original text
    String originalText = "This is my message";
    System.out.println("Original Text: " + originalText);

    // Encrypt text
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] cipherText = encryptCipher.doFinal(originalText.getBytes());
    System.out.println("Encrypted Text: " + new String(cipherText));

    // Decrypt text
    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    String decryptedText = new String(decryptCipher.doFinal(cipherText));
    System.out.println("Decrypted Text: " + decryptedText);
  }
}