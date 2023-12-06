import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_3_Req11 {
    public static void main(String[] args) {
        String secretKey = "0123456789abcdef"; 
        String initializationVector = "abcdef1234567890"; 
        String encryptedData = "QmFzZTY0IGVuY29kZWQgc3RyaW5n"; 

        try {
            String decryptedData = decrypt(secretKey, initializationVector, encryptedData);
            System.out.println("Decrypted data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String secretKey, String initializationVector, String encryptedData) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(original);
    }
}