import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class asym_encryption_1_Req30 {
    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
        publicKey = keyPairGenerator.getPair().getPublic();
        privateKey = keyPairGenerator.getPair().getPrivate();
        
        String originalMessage = "Hello World!";

        String encryptedMessage = encrypt(originalMessage, publicKey);
        String decryptedMessage = decrypt(encryptedMessage, privateKey);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    public static String encrypt(String plainText, PublicKey publicKey ) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
    }
}