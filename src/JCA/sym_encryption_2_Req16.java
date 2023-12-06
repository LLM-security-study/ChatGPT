import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_2_Req16 {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    public static void main(String[] args) throws Exception {

        String originalData = "SensitiveData";
        System.out.println("Original data: " + originalData);

        String encryptedData = encrypt(originalData);
        System.out.println("Encrypted data: " + encryptedData);

        String decryptedData = decrypt(encryptedData);
        System.out.println("Decrypted data: " + decryptedData);
    }

    public static String encrypt(String dataToEncrypt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(dataToEncrypt.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encValue);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
}