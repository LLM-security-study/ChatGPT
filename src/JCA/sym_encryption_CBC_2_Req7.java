import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req7 {
  private static final String key = "aesEncryptionKey";
  private static final String initVector = "encryptionIntVec";

  private static IvParameterSpec iv;
  private static SecretKeySpec skeySpec;

  public static void main( String [] args) throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();

    byte[] plaintext = "Hello World!".getBytes();
    System.out.println("Plaintext: " + new String(plaintext));

    byte[] ciphertext = encrypt(secretKey, plaintext);
    System.out.println("Ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));

    String decrypted = decrypt(secretKey, ciphertext);
    System.out.println("Decrypted Plaintext: " + decrypted);
  }

  private static byte[] encrypt(SecretKey key, byte[] value) throws Exception {
    iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    skeySpec = new SecretKeySpec(key.getEncoded(), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encrypted = cipher.doFinal(value);
    return encrypted;
  }

  private static String decrypt(SecretKey key, byte[] encrypted) throws Exception {
    iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    skeySpec = new SecretKeySpec(key.getEncoded(), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] original = cipher.doFinal(encrypted);
    return new String(original);
  }
}