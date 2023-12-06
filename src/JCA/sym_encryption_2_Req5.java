import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_2_Req5 {

    public static void main(String[] args) throws Exception {
        String data = "TestData";

        // Generate Key
        SecretKey secretKey = generateAESKey();
        byte[] encryptedData = encryptData(secretKey, data);
        byte[] decryptedData = decryptData(secretKey, encryptedData);

        // print original, encrypted and decrypted data
        System.out.println("Original data: " + data);
        System.out.println("Encrypted data: " + new String(encryptedData, StandardCharsets.UTF_8));
        System.out.println("Decrypted data: " + new String(decryptedData, StandardCharsets.UTF_8));
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private static byte[] encryptData(SecretKey secretKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] decryptData(SecretKey secretKey, byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(encryptedData);
    }
}