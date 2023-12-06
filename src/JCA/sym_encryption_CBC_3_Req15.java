import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req15 {
    public static void main(String[] args) throws Exception {
        // Initialize keys
        String key = "1234567812345678";  // Dummy key for example
        String initVector = "1234567812345678"; // Dummy vector for example
        
        // Encoded string which we have to decode
        String encryptedString = "vCIkqU5CqaAgaR2klYFGAA==";

        System.out.println(decrypt(key, initVector, encryptedString));
    }

    public static String decrypt(String key, String initVector, String encrypted) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        return new String(original);
    }
}