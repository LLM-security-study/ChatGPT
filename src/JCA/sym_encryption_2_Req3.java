import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_2_Req3 {
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        //initializing key
        String keyString = "aesEncryptionKey";
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(keyString.getBytes()));
        secretKey = keyGenerator.generateKey();

        //encryption
        String originalString = "This text will be encrypted";
        System.out.println("PlainText: " + originalString);
        byte[] cipherText = encrypt(originalString);
        System.out.println("Ciphered Text: " + Base64.getEncoder().encodeToString(cipherText));

        //decryption
        String decryptedText = decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static byte[] encrypt(String plainText) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return aesCipher.doFinal(plainText.getBytes());
    }

    private static String decrypt(byte[] cipherText) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytePlainText = aesCipher.doFinal(cipherText);
        return new String(bytePlainText);
    }
}