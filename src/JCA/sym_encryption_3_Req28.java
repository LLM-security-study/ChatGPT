import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class sym_encryption_3_Req28 {
    public static void main(String[] args) throws Exception {
        
        // Generate a symmetric AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        
        String originalText = "Some data to decrypt";
        byte[] originalBytes = originalText.getBytes();

        // Encrypt using the Cipher class and the secret key
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteCipherText = aesCipher.doFinal(originalBytes);

        // Decrypt using the Cipher class and the secret key
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);

        // Conversion of decrypted bytes to String 
        String decryptedText = new String(byteDecryptedText);

        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + DatatypeConverter.printHexBinary(byteCipherText));
        System.out.println("Decrypted Text: " + decryptedText);
    }
}