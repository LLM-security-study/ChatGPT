import java.security.*;
import javax.crypto.*;
import java.util.Base64;

public class asym_encryption_3_Req29 {
  public static void main(String[] args) throws Exception {
    // Generate key pair
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(1024);
    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey privateKey = pair.getPrivate();
    PublicKey publicKey = pair.getPublic();

    // Original text
    String originalText = "This is the text to be encrypted";
    System.out.println("Original Text: " + originalText);

    // Encrypt
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedBytes = cipher.doFinal(originalText.getBytes());
    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    System.out.println("Encrypted Text: " + encryptedText);

    // Decrypt
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted Text: " + decryptedText);
  }
}