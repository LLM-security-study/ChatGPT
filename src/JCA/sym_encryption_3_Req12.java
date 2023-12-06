// Necessary imports
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

public class sym_encryption_3_Req12 {
  
  // Pre-defined Cryptographic Key
  private static final String key = "aesEncryptionKey";
  
  // Pre-defined Initialization Vector
  private static final String initVector = "encryptionIntVec";
  
  public static void main(String[] args) {
    String cipherText = "U2FsdGVkX1+vupppZksvRf5pq5g5XjFRlipRkwB0K1Y96Qsv2Lm+31cmzaAILwyt";
    System.out.println("Ciphered Text: " + cipherText);

    String decryptedText = decrypt(key, initVector, cipherText);
    System.out.println("Decrypted Text: " + decryptedText);
  }
  
  public static String decrypt(String key, String initVector, String encrypted) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

      return new String(original);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }
}