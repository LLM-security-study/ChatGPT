import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_1_Req10 {

    private static KeyPair keyPair;

    static {
        try {
            keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    private static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String message = "The secret message";

        String encryptedMessage = encrypt(message, keyPair.getPublic());
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}