import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class sym_encryption_CBC_3_Req2 {
    public static void main(String []args) {
        try {
            String secretKey = "SECRET_KEY";
            String ivKey = "IV_KEY";
            String encryptedValue = "ENCRYPTED_VALUE";
            
            IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));

            System.out.println("Decrypted value : " + new String(original));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}