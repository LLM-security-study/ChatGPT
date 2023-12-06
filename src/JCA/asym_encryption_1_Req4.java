import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class asym_encryption_1_Req4 {

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.generateKeyPair();
    }

    private static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = plainText.getBytes("UTF-8");
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        return Base64.getEncoder().encodeToString(encryptedMessageBytes);
    }

    private static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        return new String(decryptedMessageBytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        String encryptedMessage = encrypt("Hello World!", keyPair.getPublic());
        System.out.println("Encrypted Message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}