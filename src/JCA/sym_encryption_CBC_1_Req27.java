import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec; 
import javax.crypto.spec.IvParameterSpec; 
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class sym_encryption_CBC_1_Req27 {
    private static String CIPHER_INSTANCE = "AES/CBC/PKCS5PADDING";
    private static String AES = "AES";
    private static String SECRET_KEY = "1234567890123456"; // Consider this as secret key with 16 character.
    private static String INITIALIZATION_VECTOR = "abcdefghijklmnop"; // Consider this as initialization vector with 16 character.
    
    private static SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
    private static IvParameterSpec IV_PARAMETER_SPEC = new IvParameterSpec(INITIALIZATION_VECTOR.getBytes());

    public static void main(String[] args) throws Exception  {
        String originalString = "SecureMessage";
        System.out.println("Original string: " + originalString);

        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted string: " + encryptedString);

        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted string: " + decryptedString);
    }
    
    public static String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY_SPEC, IV_PARAMETER_SPEC);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC, IV_PARAMETER_SPEC);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}