import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class sym_encryption_CBC_2_Req20 {

    private static final String SECRET_KEY = "MySecretKey12345";
    private static final String INITIALIZATION_VECTOR = "InitializationVe";

    public static void main(String[] args) throws Exception {
        String originalText = "Text to be encrypted";

        //Encrypt the Text
        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, getKey(), getIvParameterSpec());
        byte[] encrypted = encryptCipher.doFinal(originalText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encrypted Text: " + encryptedText);

        //Decrypt the Text
        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, getKey(), getIvParameterSpec());
        byte[] decrypted = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decrypted);
        System.out.println("Decrypted Text: " + decryptedText);

    }

    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
    }

    private static IvParameterSpec getIvParameterSpec() {
        return new IvParameterSpec(INITIALIZATION_VECTOR.getBytes());
    }

}