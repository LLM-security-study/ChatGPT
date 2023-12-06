import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class sym_encryption_2_Req19 {

    static String secretKey = "1234567812345678"; //AES key length should be 128 bit
    static String initialVector = "1234567812345678"; //initial vector length should be 128 bit

    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";
        String encryptedText = encrypt(plainText);
        String decryptedText = decrypt(encryptedText);

        System.out.println("Original Text: " + plainText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

   public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes("UTF-8"));
            Key skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes("UTF-8"));
            Key skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

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