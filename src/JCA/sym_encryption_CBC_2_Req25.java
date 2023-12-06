import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.SecureRandom;

public class sym_encryption_CBC_2_Req25 {
    public static String encrypt(String data, SecretKeySpec key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    public static void main(String[] args) throws Exception{
        // Generating random key and initialization vector
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(key);
        rand.nextBytes(iv);

        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        String data = "Encrypt this!";
        System.out.println("Encrypted data: " + encrypt(data, secretKey, ivSpec));
    }
}