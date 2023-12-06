import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_2_Req3 {

    private static String initVector = "randomInitVector"; // 16 bytes IV
    private static String key = "encryptionKey123"; // 128 bit key

    public static void main(String[] args) {
        
        String original = "Important Message";
        System.out.println("Original message: " + original);

        try {
            String encrypted = encrypt(key, initVector, original);
            System.out.println("Encrypted message: " + encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String key, String initVector, String value) throws Exception{
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }
}