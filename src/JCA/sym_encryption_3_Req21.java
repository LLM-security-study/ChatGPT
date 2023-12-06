import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class sym_encryption_3_Req21{
    public static void main(String []args) throws Exception {
        String plainText = "This is a plain text which needs to be encrypted and decrypted.";
        
        // Generate key
        SecretKey secKey = KeyGenerator.getInstance("AES").generateKey();
        
        // Encrypt the plain text
        String encryptedText = encryptText(plainText, secKey);
        System.out.println("The encrypted text is: " + encryptedText);
        
        // Decrypt the encrypted text
        String decryptedText = decryptText(encryptedText, secKey);
        System.out.println("The decrypted text is: " + decryptedText);
    }
    
    // Method for encryption
    public static String encryptText(String plainText, SecretKey secKey) throws Exception {
        // AES is symmetric algorithm we are using for encryption.
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());

        return new BASE64Encoder().encode(byteCipherText);
    }
    
    // Method for decryption
    public static String decryptText(String encryptedText, SecretKey secKey) throws Exception {
        // AES is symmetric algorithm we are using for encryption.
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(new BASE64Decoder().decodeBuffer(encryptedText));
        return new String(bytePlainText);
    }
}