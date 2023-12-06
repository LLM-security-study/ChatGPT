import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req24 {
    private static final String ALGORITHM = "AES";
    private static final String MODE = "AES/CBC/PKCS5Padding";
    private static final String IV = "AAAAAAAAAAAAAAAA"; // you can change this to any 16 characters
    private static final String SECRET_KEY = "YOUR_SECRET_KEY"; // replace this with your secret key

    public static void main(String[] args) throws Exception {
        String originalString = "PlainText";

        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted Text : " + encryptedString);

        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted Text : " + decryptedString);
    }

    public static String encrypt(String plainText) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(generateIV());
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), ALGORITHM);

        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(generateIV());
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), ALGORITHM);

        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(cipherText));

        return new String(original);
    }

    public static byte[] generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}