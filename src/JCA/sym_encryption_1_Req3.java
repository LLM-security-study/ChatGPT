import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_1_Req3 {
    private static final String ALGORITHM = "AES";
    
    // secret key for AES (16 chars = 128 bits)
    private static final String SECRET_KEY = "YourSecretKeyHere";

    public static String encrypt(String valueToEncrypt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = c.doFinal(valueToEncrypt.getBytes());
        String encryptedBase64Value = Base64.getEncoder().encodeToString(encryptedValue);
        return encryptedBase64Value;
    }

    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = c.doFinal(decodedValue);
        return new String(decryptedValue);
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        return key;
    }

    public static void main(String[] args) throws Exception {
        String valueToEncrypt = "Hello, World!";
        String encryptedValue = encrypt(valueToEncrypt);
        System.out.println("Encrypted value: " + encryptedValue);
        String decryptedValue = decrypt(encryptedValue);
        System.out.println("Decrypted value: " + decryptedValue);
    }
}