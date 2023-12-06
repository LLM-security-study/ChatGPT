import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_1_Req22 {

    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[128 / 8];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static SecretKey generateKey(int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    public static void main(String[] args) {
        try {
            String algorithm = AES_CBC_PADDING;
            SecretKey key = generateKey(256);

            IvParameterSpec iv = generateIv();
            String originalString = "Text to be encrypted and decrypted";

            String encryptedString = encrypt(algorithm, originalString, key, iv);
            System.out.println("Encrypted Text: " + encryptedString);

            String decryptedString = decrypt(algorithm, encryptedString, key, iv);
            System.out.println("Decrypted Text: " + decryptedString);
        } catch (Exception e) {
            System.out.println("Error while encrypting/decrypting");
        }
    }
}