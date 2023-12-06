import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req30 {

  public static void main(String[] args) {
    String key = "aesEncryptionKey";
    String initVector = "encryptionIntVec";
    String encryptedData = "zKwYhgVUG+sHZAH/0D7TIg=="; // this is just an example

    System.out.println(decrypt(key, initVector, encryptedData));
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