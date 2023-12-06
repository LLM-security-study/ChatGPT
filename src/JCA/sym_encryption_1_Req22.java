import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_1_Req22 {
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decipheredText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decipheredText);
    }

    public static void main(String[] args) throws Exception {
        String plainText = "Symmetric Encryption";
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Encrypted Text: " + encryptedText);
        
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}