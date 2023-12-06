import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_3_Req30 {

    public static void main(String[] args) throws Exception {

        //Same key was used for encryption
        String secretKey = "aRandomGeneratedOrUserDefinedKey";
        
        // Encrypted data
        String encryptedData = "Hcp30T8A+SpUSfGZku7+qg==";

        String decryptedData = decryptData(encryptedData, secretKey);

        System.out.println("Decrypted Data: " + decryptedData);
    }

    public static String decryptData(String encryptedData, String secretKey) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedVal = cipher.doFinal(decodedValue);
        return new String(decryptedVal);
    }
}