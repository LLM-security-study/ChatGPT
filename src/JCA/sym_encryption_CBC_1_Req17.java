import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_1_Req17 {
    private static final String initVector = "encryptionIntVec"; // 16 bytes IV

    public static void main(String[] args) {
        String originalString = "Hello, World!";
        System.out.println("Original String: " + originalString);

        try {
            String encrypt = toEncrypt("mysecretpassword", originalString);
            System.out.println("Encrypted String: " + encrypt);

            String decrypt = toDecrypt("mysecretpassword", encrypt);
            System.out.println("Decrypted String: " + decrypt);
        } catch (Exception e) {
            System.out.println("Error while encrypting/decrypting");
            e.printStackTrace();
        }
    }

    public static String toEncrypt(String key, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String toDecrypt(String key, String encrypted) {
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