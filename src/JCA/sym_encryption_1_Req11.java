import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_1_Req11 {
  public static void main(String[] args) throws Exception {
    String originalString = "My secret message";
    
    SecretKey secretKey = getSecretKey();
    String encryptedString = encryptString(originalString, secretKey);
    String decryptedString = decryptString(encryptedString, secretKey);
    
    System.out.println("Original string: " + originalString);
    System.out.println("Encrypted string: " + encryptedString);
    System.out.println("Decrypted string: " + decryptedString);
  }
  
  private static SecretKey getSecretKey() throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey;
  }
  
  private static String encryptString(String originalString, SecretKey secretKey) throws Exception {
    byte[] originalBytes = originalString.getBytes("UTF-8");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(originalBytes);
    String encryptedString = new String(encryptedBytes, "UTF-8");
    return encryptedString;
  }
  
  private static String decryptString(String encryptedString, SecretKey secretKey) throws Exception {
    byte[] encryptedBytes = encryptedString.getBytes("UTF-8");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    String decryptedString = new String(decryptedBytes, "UTF-8");
    return decryptedString;
  }
}