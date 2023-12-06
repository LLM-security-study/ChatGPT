import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_1_Req7 {
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'y', 'm', 'm', 'e', 't', 'r', 'i', 'c'};

    public static void main(String[] args) throws Exception {
        String text = "Hello World";

        String encryptedText = encrypt(text);
        String decryptedText = decrypt(encryptedText);

        System.out.println("Original Text  : " + text);
        System.out.println("Encrypted Text : " + encryptedText);
        System.out.println("Decrypted Text : " + decryptedText);
    }

    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encValue);
        return encryptedValue;
    }

    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        return key;
    }
}