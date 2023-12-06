import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_1_Req29 {
    private static final String AES = "AES";
    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";
    private static byte[] IV = new byte[16];

    public static void main(String[] args) throws Exception {
        // The key has to be of length 16 for AES-128-CBC
        String secretKey = "This is a secret";
        String originalString = "Encrypt this string";

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(IV);

        String encryptedString = encrypt(originalString, secretKey);
        String decryptedString = decrypt(encryptedString, secretKey);

        System.out.println("Original string: " + originalString);
        System.out.println("Encrypted string: " + encryptedString);
        System.out.println("Decrypted string: " + decryptedString);
    }

    public static String encrypt(String strToEncrypt, String secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            SecretKey key = new SecretKeySpec(secretKey.getBytes(), AES);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            SecretKey key = new SecretKeySpec(secretKey.getBytes(), AES);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}