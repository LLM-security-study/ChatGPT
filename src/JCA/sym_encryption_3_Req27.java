import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;


public class sym_encryption_3_Req27 {
    public static void main(String[] args) {
        try {
            String key = "1234567890123456"; // 128 bit key 
            String encryptedData = "BD735C8FE7C3A4A8D6C15F91F7B948AE"; // Dummy encrypted data
            String decryptData = decrypt(encryptedData, key);
            System.out.println("Decrypted Data: " + decryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        Key key = generateKey(secretKey);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedVal = c.doFinal(decodedValue);
        return new String(decryptedVal);
    }

    private static Key generateKey(String secretKey) throws Exception {
        return new SecretKeySpec(secretKey.getBytes(), "AES");
    }
}