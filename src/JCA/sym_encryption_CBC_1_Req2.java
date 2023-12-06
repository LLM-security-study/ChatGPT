import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_1_Req2 {

    private static final String algorithm = "AES/CBC/PKCS5PADDING";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'e', 'y', 'K', 'e', 'y', '1', '2', '3', '4'};
    private static final byte[] ivValue =
            new byte[]{'T', 'h', 'i', 's', 'I', 's', 'I', 'V', '1', '2', '3', '4', '5', '6', '7', '8'};

    public static String encrypt(String valueToEncrypt) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(ivValue);
        SecretKey key = new SecretKeySpec(keyValue, "AES");

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encrypted = cipher.doFinal(valueToEncrypt.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String valueToDecrypt) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(ivValue);
        SecretKey key = new SecretKeySpec(keyValue, "AES");

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(valueToDecrypt));

        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        String value = "Hello AESCBC encryption";
        String encryptedValue = encrypt(value);
        System.out.println("Encrypted Value : " + encryptedValue);

        String decryptedValue = decrypt(encryptedValue);
        System.out.println("Decrypted Value : " + decryptedValue);
    }
}