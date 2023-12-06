import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_CBC_1_Req24 {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "a5s8d2e9v5y8n9d8"; //16 bytes secret key
    private static final String INIT_VECTOR = "a5b7c8d9e0f1g2h3"; // 16 bytes IV 

    public static String encrypt(String dataToEncrypt) throws Exception {

        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        Key keySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encrypted = cipher.doFinal(dataToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {

        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        Key keySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(original);
    }

    public static void main(String[] args) throws Exception {

        String data = "Hello, World!";
        
        String encryptedData = encrypt(data);
        System.out.println("Encrypted data: " + encryptedData);
        
        String decryptedData = decrypt(encryptedData);
        System.out.println("Decrypted data: " + decryptedData);
    }
}