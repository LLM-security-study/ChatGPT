import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_1_Req25 {
    public static void main(String[] args) throws Exception {
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        final String initVector = "encryptionIntVec"; // 16 bytes IV

        String originalString = "hello world";
        System.out.println("Original String to encrypt - " + originalString);
        String encryptedString = encrypt(originalString, secretKey, initVector);
        System.out.println("Encrypted String - " + encryptedString);
        String decryptedString = decrypt(encryptedString, secretKey, initVector);
        System.out.println("After decryption - " + decryptedString);
    }

    public static String encrypt(String value, String key, String initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted, String key, String initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}