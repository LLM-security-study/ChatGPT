import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class sym_encryption_CBC_3_Req21 {
  public static void main(String[] args) {
    String data = "Encrypted data goes here";
    String key = "your-key-goes-here";
    String initVector = "init-vector-here"; // should be 16 bytes
    
    try {
      String decryptedData = decrypt(key, initVector, data);
      System.out.println("Decrypted data: " + decryptedData);
    } catch (GeneralSecurityException e) {
      System.out.println("Decryption error: " + e.getMessage());
    }
  }

  public static String decrypt(String key, String initVector, String encrypted) throws GeneralSecurityException {
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

    byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

    return new String(original);
  }
}