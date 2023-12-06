import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req8 {
    private static String algorithm = "AES";
    private static byte[] keyValue = new byte[]{'A', 'S', 'E', 'E', 'N', 'C', 'R', 'Y', 'P', 'T', 'I', 'O', 'N', 'T', 'E', 'S'};

    // Performs Encryption
    public static String encrypt(String plainText) throws Exception {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    // generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }

    // Test the Encryption and Decryption method
    public static void main(String[] args) throws Exception {

        String plainText = "Hello World";
        String encryptedText = sym_encryption_3_Req8.encrypt(plainText);
        String decryptedText = sym_encryption_3_Req8.decrypt(encryptedText);

        System.out.println("Plain Text : " + plainText);
        System.out.println("Encrypted Text : " + encryptedText);
        System.out.println("Decrypted Text : " + decryptedText);
    }
}