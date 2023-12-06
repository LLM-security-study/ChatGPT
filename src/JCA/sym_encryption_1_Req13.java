import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_1_Req13 {

   public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedText);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedText);
    } 

    public static void main(String[] args) throws Exception {
        String originalString = "This is a secret message";
        
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        
        String encryptedString = encrypt(originalString, secretKey);
        String decryptedString = decrypt(encryptedString, secretKey);
        
        System.out.println("Original String: " + originalString);
        System.out.println("AES Encrypted String: " + encryptedString);
        System.out.println("AES Decrypted String: " + decryptedString);
    }
}