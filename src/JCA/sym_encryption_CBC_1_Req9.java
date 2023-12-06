import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_CBC_1_Req9 {
    static String algorithm = "AES/CBC/PKCS5PADDING";
    static SecretKey key;
    static byte[] iv;

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            key = keyGenerator.generateKey();
            iv = new byte[128 / 8];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String plainText = "Hello, World!";
        System.out.println("Plain Text: " + plainText);

        String cipherText = encrypt(plainText);
        System.out.println("Encrypted Text: " + cipherText);

        String decryptedText = decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String encrypt (String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt (String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }
}