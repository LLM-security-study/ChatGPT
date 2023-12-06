import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class sym_encryption_2_Req8 {
    static SecretKey secretKey;

    public static void main(String[] args) {
        try {
            String plainText = "Hello, World!";

            // Generate key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();

            // Encrypt text
            String encryptedText = encrypt(plainText);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt text
            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedCipherText = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedText = cipher.doFinal(decodedCipherText);
            return new String(decryptedText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}