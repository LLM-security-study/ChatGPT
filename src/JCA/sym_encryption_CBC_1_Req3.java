import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.security.SecureRandom;

public class sym_encryption_CBC_1_Req3 {
    public static void main(String[] args) throws Exception {
        // Original text
        String originalString = "This is a secret message";
        System.out.println("Original string: " + originalString);
        
        // Generate Key
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        // Encrypt text
        String encryptedString = encrypt(originalString, secretKey);
        System.out.println("Encrypted string: " + encryptedString);

        // Decrypt text
        String decryptedString = decrypt(encryptedString, secretKey);
        System.out.println("Decrypted string: " + decryptedString);
    }

    public static String encrypt(String strToEncrypt, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            final String encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            return Base64.getEncoder().encodeToString(iv) + ":" + encryptedString;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, SecretKey secretKey) {
        try {
            String[] parts = strToDecrypt.split(":");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.getDecoder().decode(parts[0]));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            final String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(parts[1])));
            return decryptedString;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}