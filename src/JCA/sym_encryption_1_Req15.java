import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_1_Req15 {
    
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static String encrypt(String value) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String secretString = "Hello World!";
        String encryptedString = encrypt(secretString);
        System.out.println("Encrypted Text : " + encryptedString);
        System.out.println("Decrypted Text : " + decrypt(encryptedString));
    }
}