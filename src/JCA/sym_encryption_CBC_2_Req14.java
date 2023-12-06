import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class sym_encryption_CBC_2_Req14 {
    public static void main(String[] args)  {
        try {
            String text = "Text to be encrypted using AES encryption";
            String key = "MySecretKey1234!"; // Must be 16 characters
            final String initVector = "InitializationVe"; // Must be 16 characters

            System.out.println("Text before Encryption: " + text);

            String encrypted = encrypt(key, initVector, text);

            System.out.println("Text after Encryption: " + encrypted);

            String decrypted = decrypt(key, initVector, encrypted);

            System.out.println("Text after decryption: " + decrypted);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String encrypt(String key, String initVector, String text) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String key, String initVector, String encryptedText) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        return new String(original);
    }
}