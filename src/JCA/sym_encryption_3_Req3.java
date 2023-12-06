import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_3_Req3 {
    private static final String ALGORITHM = "AES";
    public static void main(String[] args) {

        String secretKey = "1234567890abcdef";  // Your secret key (it's just an example)
        String encryptedData = "encrypted data";  // Replace it with your encrypted data

        try {
            String decryptedData = decrypt(encryptedData, secretKey);
            System.out.println("Decrypted data: " + decryptedData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String decrypt(String data, String key) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByteValue = cipher.doFinal(data.getBytes("utf-8"));
        String decryptedValue = new String(decryptedByteValue, "utf-8");
        return decryptedValue;
    }
}