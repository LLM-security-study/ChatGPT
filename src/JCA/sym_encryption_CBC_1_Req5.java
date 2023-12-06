import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_1_Req5 {

    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        String plaintext = "This is a test string.";
        System.out.println("Original text: " + plaintext);

        byte[] cipherText = encrypt(plaintext.getBytes(), secretKey, IV);
        System.out.println("Encrypted text: " + Base64.getEncoder().encodeToString(cipherText));

        String decryptedText = decrypt(cipherText, secretKey, IV);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        return cipher.doFinal(plaintext);
    }

    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes);
    }
}