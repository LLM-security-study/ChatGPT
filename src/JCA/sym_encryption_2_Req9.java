import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class sym_encryption_2_Req9 {

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private static String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(key), "AES"));
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    private static String decrypt(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(key), "AES"));
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    public static void main(String[] args) throws Exception {
        String data = "Hello, World!";
        SecretKey secretKey = generateKey();
        String stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Original Data : " + data);

        String encryptedData = encrypt(data, stringKey);
        System.out.println("Encrypted Data : " + encryptedData);

        String decryptedData = decrypt(encryptedData, stringKey);
        System.out.println("Decrypted Data : " + decryptedData);
    }
}