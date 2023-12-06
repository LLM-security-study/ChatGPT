import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_2_Req12 {
  public static void main(String[] args) throws Exception {
    // Generate a secret key
    KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
    SecretKey myDesKey = keygenerator.generateKey();

    // Create a cipher object to encrypt and decrypt
    Cipher desCipher;
    desCipher = Cipher.getInstance("AES");

    // Initialize the cipher for encryption with the key
    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

    // Our message
    byte[] text = "Hello, World!".getBytes();

    // Encrypt the text
    byte[] textEncrypted = desCipher.doFinal(text);

    System.out.println("Text Encrypted: " + new String(textEncrypted));

    // Initialize the same cipher for decryption
    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

    // Decrypt the text
    byte[] textDecrypted = desCipher.doFinal(textEncrypted);

    System.out.println("Text Decrypted : " + new String(textDecrypted));
  }
}