import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class sym_encryption_1_Req24 {

    public static void main(String[] args) throws Exception {

        // Generate secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Specifies the length of key
        SecretKey secretKey = keyGenerator.generateKey();

        // String that needs to be encrypted
        String stringToEncrypt = "Text to encrypt";

        // Encrypt the string
        String encryptedString = encrypt(secretKey, stringToEncrypt);
        System.out.println("Encrypted text: " + encryptedString);

        // Decrypt the string
        String decryptedString = decrypt(secretKey, encryptedString);
        System.out.println("Decrypted text: " + decryptedString);
    }

    public static String encrypt(SecretKey secretKey, String stringToEncrypt) throws Exception {
       Cipher cipher = Cipher.getInstance("AES");
       cipher.init(Cipher.ENCRYPT_MODE, secretKey);
       byte[] encryptedByte = cipher.doFinal(stringToEncrypt.getBytes());
       Base64.Encoder encoder = Base64.getEncoder();
       String encryptedString = encoder.encodeToString(encryptedByte);
       return encryptedString;
    }

    public static String decrypt(SecretKey secretKey, String stringToDecrypt) throws Exception {
       Base64.Decoder decoder = Base64.getDecoder();
       byte[] decryptedByte = decoder.decode(stringToDecrypt);
       Cipher cipher = Cipher.getInstance("AES");
       cipher.init(Cipher.DECRYPT_MODE, secretKey);
       String decryptedString = new String(cipher.doFinal(decryptedByte));
       return decryptedString;
    }
}