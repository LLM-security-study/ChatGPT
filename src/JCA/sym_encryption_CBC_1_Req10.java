import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_1_Req10 {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] IV = new byte[16]; // Initialization vector (can be changed)
    private static final String SECRET_KEY = "abcdefghijklmnop"; // Secret key (must be 16 characters)

    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec ivParameters = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameters);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while encrypting data", e);
        }
    }

    public static String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec ivParameters = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameters);
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while decrypting data", e);
        }
    }

    public static void main(String[] args) {
        String text = "My testing text";
        String cipherText = encrypt(text);
        System.out.println("Encrypted text: " + cipherText);
        System.out.println("Decrypted text: " + decrypt(cipherText));
    }

}