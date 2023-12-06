import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_2_Req11 {
    public static void main(String[] args) throws Exception {
        // Generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();

        // Generate Cipher
        Cipher aesCipher = Cipher.getInstance("AES");

        // Initial input text
        String plainText = "This is a symmetric cryptography example";

        // Encrypt the text
        aesCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        System.out.println("Cipher Text: " + new String(byteCipherText));

        // Decrypt the text
        aesCipher.init(Cipher.DECRYPT_MODE, key, aesCipher.getParameters());
        byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
        System.out.println("Decrypted Text: " + new String(byteDecryptedText));
    }
}