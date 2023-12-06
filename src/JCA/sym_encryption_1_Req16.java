import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req16 {

    private static final String SECRET_KEY = "mySecretKey";  // 128 bit key

    public static void main(String[] args) throws Exception {

        String originalString = "hello world";

        String encryptedString = encrypt(originalString);
        System.out.println(encryptedString);
        
        String decryptedString = decrypt(encryptedString);
        System.out.println(decryptedString);

    }

    public static String encrypt(String strToEncrypt)   {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] utf8 = strToEncrypt.getBytes(StandardCharsets.UTF_8);
            byte[] enc = cipher.doFinal(utf8);
            
            return Base64.getEncoder().encodeToString(enc);
            
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt)   {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            byte[] dec = Base64.getDecoder().decode(strToDecrypt);
            byte[] utf8 = cipher.doFinal(dec);
            
            return new String(utf8, StandardCharsets.UTF_8);
            
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}