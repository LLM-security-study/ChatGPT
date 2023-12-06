import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_3_Req17 {
    public static void main(String[] args) {
        String encryptedData = "YOUR_ENCRYPTED_DATA";  // Ensure this is base64 encoded
        String secretKey = "YOUR_SECRET_KEY";  // Ensure this is non-public and unique

        String decryptedData = decryptData(encryptedData, secretKey);

        System.out.println("Decrypted Data: " + decryptedData);
    }

    public static String decryptData(String data, String key){
        try {
            byte[] byteKey = key.getBytes();
            Key aesKey = new SecretKeySpec(byteKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // decrypt the data
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decodedData = Base64.getDecoder().decode(data);
            byte[] decryptedData = cipher.doFinal(decodedData);

            return new String(decryptedData);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}