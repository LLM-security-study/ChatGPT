import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_1_Req1 {

    private static final String SECRET_KEY = "1234567890123456"; // 16 chars = 128 bit
    private static final String IV_STRING = "1234567890123456"; // IV should be 16 bytes

    public static void main(String[] args) throws Exception {
        String originalString = "Hello, World!";

        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted string: " + encryptedString);

        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted string: " + decryptedString);
    }

    public static String encrypt(String strToEncrypt) throws Exception {
        IvParameterSpec ivspec = new IvParameterSpec(IV_STRING.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decrypt(String strToDecrypt) throws Exception {
        IvParameterSpec ivspec = new IvParameterSpec(IV_STRING.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
}