import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req18 {
    public static void main(String[] args) throws Exception {
        String encryptedData = "Encrypted Data Here"; // Replace with your encrypted data
        String key = "key here"; // Replace with your key
        String initVector = "initialization vector here"; // Replace with your initialization vector
        
        byte[] keyBytes = key.getBytes("UTF-8");
        byte[] ivBytes = initVector.getBytes("UTF-8");
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] original = cipher.doFinal(encryptedBytes);

        System.out.println(new String(original));
    }
}