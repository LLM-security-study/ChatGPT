import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req2 {

    private static final String ALGORITHM = "RSA";

    // Function to encrypt string
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

    // Function to decrypt string
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

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(1024); // Key size

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String data = "text to encrypt";
        System.out.println("Original Data: " + data);

        // Encryption
        byte[] encryptedData = encrypt(data, keyPair.getPublic());
        System.out.println("Encrypted Data: " + new String(encryptedData));

        // Decryption
        String decryptedData = decrypt(encryptedData, keyPair.getPrivate());
        System.out.println("Decrypted Data: " + decryptedData);
    }

}