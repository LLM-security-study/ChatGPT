import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;

public class sym_encryption_CBC_1_Req15 {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] KEY = new byte[16];
    private static final byte[] IV = new byte[16];

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String []args) {
        String originalString = "test";

        String encryptedString = encrypt(originalString);
        String decryptedString = decrypt(encryptedString);

        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}