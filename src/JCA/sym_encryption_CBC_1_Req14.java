import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_1_Req14 {

    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

    private static byte[] iv = new byte[16];

    public static void main(String[] args) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        // a simple key for testing. In real usage this should be more secure.
        String encryptionKey = "1234567812345678";

        String originalText = "Hello, world!";

        String encryptedText = encrypt(originalText, encryptionKey);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, encryptionKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String encrypt(String textToEncrypt, String encryptionKey) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedText = cipher.doFinal(textToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedText);
    }

    public static String decrypt(String textToDecrypt, String decryptionKey) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(decryptionKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decodedEncryptionBytes = Base64.getDecoder().decode(textToDecrypt);
        byte[] decryptionBytes = cipher.doFinal(decodedEncryptionBytes);

        return new String(decryptionBytes);
    }
}