import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req7 {

    private static final String KEY = "{your AES key here}";
    private static final String INITIALIZATION_VECTOR = "{your initialization vector here}";

    public static void main(String[] args) {
        String cipherText = "{insert the cipher text here}";
        System.out.println(decrypt(cipherText));
    }

    private static String decrypt(String cipherText) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(KEY), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(INITIALIZATION_VECTOR));
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}