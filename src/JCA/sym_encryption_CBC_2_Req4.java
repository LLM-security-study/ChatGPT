import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_2_Req4 {
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String SECRET_KEY = "MySecretKey12345"; 
    private static final String IV_STRING = "16_Byte__String_"; // must be 16 bytes

    public static void main(String[] args) {
        String originalString = "This is the data to be encrypted";
        System.out.println("Original string: " + originalString);
        try {
            String encryptedString = encrypt(originalString, SECRET_KEY);
            System.out.println("Encrypted string: " + encryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String dataToEncrypt, String secretKey) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(IV_STRING.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(dataToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}