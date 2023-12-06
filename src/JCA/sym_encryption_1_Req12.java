import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req12 {
    private static final String KEY = "1234567812345678"; // 128 bit key

    public static void main(String[] args) throws Exception {
        String toEncrypt = "A important message";
        System.out.println("Encrypting...");
        String encrypted = encrypt(toEncrypt);
        System.out.println("Encrypted Text: " + encrypted);

        System.out.println("Decrypting...");
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }

    public static String encrypt(String strToEncrypt) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final String encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        return encryptedString;
    }

    public static String decrypt(String strToDecrypt) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        final String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        return decryptedString;
    }
}