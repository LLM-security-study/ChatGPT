import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_1_Req1 {

    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "SecretKeyToChange".getBytes();

    public static void main(String[] args) throws Exception {
        String toBeEncrypted = "Hello, World!";
        String encrypted = encrypt(toBeEncrypted);
        String decrypted = decrypt(encrypted);

        System.out.println("Original string: " + toBeEncrypted);
        System.out.println("Encrypted string: " + encrypted);
        System.out.println("Decrypted string: " + decrypted);
    }

    public static String encrypt(String value) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        
        // Use Base64 Encoder to get a readable string form of the encrypted bytes
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String value) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(value));

        return new String(decryptedValue);
    }
}