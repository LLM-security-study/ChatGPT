import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_3_Req28 {
  
  private static final String KEY = "aesEncryptionKey";
  private static final String INIT_VECTOR = "encryptionIntVec";

  public static void main(String[] args) {
    String encryptedData = "Yi4nBe4N17nrCV3fPOYbFg==";
    
    try {
      System.out.println("Decrypted Data: " + decrypt(KEY, INIT_VECTOR, encryptedData));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
  }

  public static String decrypt(String key, String initVector, String encrypted) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    
    byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

    return new String(original);
  }
}