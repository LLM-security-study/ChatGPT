import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_CBC_3_Req5 {
  
  // Replace this with your actual IV.
  private static final String INITIALIZATION_VECTOR_STRING = "****************";
  private static final byte[] INITIALIZATION_VECTOR = INITIALIZATION_VECTOR_STRING.getBytes();

  // Replace this with your actual secret key.
  private static final String SECRET_KEY_STRING = "****************";
  private static final byte[] SECRET_KEY = SECRET_KEY_STRING.getBytes();

  public static void main(String[] args) {
        String encryptedData = "EncryptedDataHere";
        String decryptedData = decryptAES(encryptedData.getBytes());
        System.out.println("Decrypted data: " + decryptedData);
  }

  private static String decryptAES(byte[] encryptedData) {
        try {
              Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
              Key keySpec = new SecretKeySpec(SECRET_KEY, "AES");
              IvParameterSpec ivSpec = new IvParameterSpec(INITIALIZATION_VECTOR);
              cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

              byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
              return new String(decryptedData);
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
        return null;
  }
}