import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req6 {
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_KEY = "0123456789abcdef"; // Change this with the correct Encryption Key
    private static final String INITIALIZATION_VECTOR = "abcdef0123456789"; // Change this with the correct Initialization Vector.
    public static void main(String [] args) {
        String encryptedText = "Yleh6E7N63LoYuAfp0FEiXntceDeybIiuPoK4R8NaDU="; // Here you can replace it with the text you want to encrypt
        System.out.println("Encrypted Text: " + encryptedText);
        String decryptedText = decrypt(encryptedText, ENCRYPTION_KEY, INITIALIZATION_VECTOR);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String decrypt(String encryptedText, String encryptionKey, String initializationVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes("UTF-8"));
            SecretKey skeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}