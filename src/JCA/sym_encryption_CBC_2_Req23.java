import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class sym_encryption_CBC_2_Req23 {
  
  static String secretKey = "aesEncryptionKey";
  static String iv = "encryptionIntVec";

    public static void main(String[] args) {
        String originalStr = "This is a test message.";
        System.out.println("Original String: " + originalStr);

        String encryptedStr = encrypt(originalStr);
        System.out.println("Encrypted String: " + encryptedStr);

        String decryptedStr = decrypt(encryptedStr);
        System.out.println("Decrypted String: " + decryptedStr);
    }

    public static String encrypt(String value) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}