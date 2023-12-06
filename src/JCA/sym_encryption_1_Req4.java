import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class sym_encryption_1_Req4 {
    static Cipher cipher;
    
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        String plainText = "This is a plain text";
        
        cipher = Cipher.getInstance("AES");
        
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Encrypted Text: " + encryptedText);
        
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }
    
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        String encryptedText = new String(encryptedByte);
        return encryptedText;
    }
    
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        byte[] encryptedTextByte = encryptedText.getBytes();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
}