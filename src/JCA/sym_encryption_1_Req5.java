import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req5 {

    private static final String AES = "AES";
    private static final String SECRET_KEY = "MySecretKey12345"; // must be 16 characters

    public static String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        String stringToEncrypt = "Hello, World!";
        String encryptedString = encrypt(stringToEncrypt);
        String decryptedString = decrypt(encryptedString);

        System.out.println("Original String: " + stringToEncrypt);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}