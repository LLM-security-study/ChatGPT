import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_1_Req16 {

    private static final String ALGORITHM = "RSA";

    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static String decrypt(byte[] text, PrivateKey key) {
        byte[] decryptedText = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptedText = cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptedText);
    }

    public static void main(String[] args) {

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(1024);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String message = "Hello, world!";

            byte[] encrypted = encrypt(message, keyPair.getPublic());

            String decrypted = decrypt(encrypted, keyPair.getPrivate());

            System.out.println("Original Message: " + message +
                    "\nEncrypted Message: " + new String(encrypted, "UTF8") +
                    "\nDecrypted Message: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}