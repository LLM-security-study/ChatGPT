import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class sym_encryption_1_Req14 {
  public static void main(String[] args) throws Exception {
    SecretKey key = generateKey();
    String plainText = "This is a test message";

    String encryptedText = encrypt(plainText, key);
    System.out.println("Encrypted Text: " + encryptedText);

    String decryptedText = decrypt(encryptedText, key);
    System.out.println("Decrypted Text: " + decryptedText);
  }

  public static SecretKey generateKey() throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128); // key length
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey;
  }

  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedText = cipher.doFinal(plainText.getBytes());
    return new String(encryptedText, "UTF-8");
  }

  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    byte[] encryptedTextBytes = encryptedText.getBytes("UTF-8");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
    return new String(decryptedTextBytes, "UTF-8");
  }
}