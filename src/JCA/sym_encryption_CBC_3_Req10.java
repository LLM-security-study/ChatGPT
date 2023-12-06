import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_3_Req10 {

    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String SECRET_KEY = "your16characters";

    public static void main(String[] args) throws Exception {

        String encryptedData = "abcdefghijkl"; // assuming this is your encrypted data

        String decryptedData = decrypt(encryptedData, SECRET_KEY);

        System.out.println("Decrypted data: " + decryptedData);
    }

    public static String decrypt(String cipherText, String secret) throws Exception {
        IvParameterSpec ivspec = new IvParameterSpec(secret.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        byte[] decodedCipherText = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);

        return new String(decryptedBytes);
    }
}