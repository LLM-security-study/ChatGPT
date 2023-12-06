import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req3 {
    public static void main(String[] args) {
        String encryptedData = "YOUR_ENCRYPTED_DATA";
        String secretKey = "YOUR_SECRET_KEY";
        String initializationVector = "YOUR_INITIALIZATION_VECTOR"; //Must be 16 bytes 

        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(initializationVector.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] originalBytes = cipher.doFinal(encryptedBytes);
            String originalData = new String(originalBytes);

            System.out.println("Original data: " + originalData);
        } catch (Exception e) {
            System.out.println("Decryption error: " + e.toString());
        }
    }
}