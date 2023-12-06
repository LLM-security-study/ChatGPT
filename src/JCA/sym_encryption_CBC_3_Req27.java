import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req27 {
    public static void main(String[] args) {
        String key = "AESEncryptionKey"; // 16 chars = 128 bit
        String initVector = "EncryptionIntVec"; // 16 chars = 128 bit

        String encrypted = "vDVnvl5CDM8NfNfyIXAdtg=="; // Sample encrypted string

        System.out.println(decrypt(key, initVector, encrypted));
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}