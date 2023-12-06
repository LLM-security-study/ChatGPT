import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_2_Req5 {
  public static void main(String[] args) throws Exception {
    String key = "aesEncryptionKey";
    String initVector = "encryptionIntVec";

    System.out.println(encrypt(key, initVector, "Hello World!"));
  }

  public static String encrypt(String key, String initVector, String value) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

      byte[] encrypted = cipher.doFinal(value.getBytes());

      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }
}