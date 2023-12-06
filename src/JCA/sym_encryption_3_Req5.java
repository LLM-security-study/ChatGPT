import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_3_Req5 {

    public static void main(String[] args) {

        // sample clear text data
        String cipherText = "encryptedText";

        // key used for encryption/decryption
        String key = "myEncryptionKey";
        try {
            // encrypt the text
            String decryptedText = decryptText(cipherText, key);

            System.out.println("Decrypted Text: " + decryptedText);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String decryptText(String cipherText, String key)
            throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        //set to decrypt mode
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));

        byte[] decryptedByteValue = cipher.doFinal(cipherTextBytes);

        String decryptedText = new String(decryptedByteValue, StandardCharsets.UTF_8);

        return decryptedText;
    }
}