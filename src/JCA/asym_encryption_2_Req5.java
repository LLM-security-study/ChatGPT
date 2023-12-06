import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req5 {
  public static void main(String[] args) throws Exception {

    // Generate key pair
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // Input data
    String originalData = "Hello, world!";

    // Encryption
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedData = encryptCipher.doFinal(originalData.getBytes());

    // Decryption
    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedData = decryptCipher.doFinal(encryptedData);

    System.out.println("Original data: " + originalData);
    System.out.println("Encrypted data: " + new String(encryptedData));
    System.out.println("Decrypted data: " + new String(decryptedData));
  }
}