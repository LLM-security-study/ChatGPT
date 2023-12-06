import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req27 {

    private static final String encryptionKey = "mypersonalsecret"; //for simplicity, we use hard coded key

    public static void main(String[] args) throws Exception {
        String originalString = "Hello World";

        String encrypted = encrypt(originalString);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");

        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");

        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(original, StandardCharsets.UTF_8);
    }
}