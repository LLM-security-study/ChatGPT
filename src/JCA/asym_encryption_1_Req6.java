import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class asym_encryption_1_Req6 {
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decryptCipher.doFinal(bytes));
    }

    public static void main(String[] args) throws Exception {
        KeyPair pair = generateKeyPair();

        String cipherText = encrypt("Hello, world!", pair.getPublic());

        System.out.println("cipherText=" + cipherText);
        String decryptedText = decrypt(cipherText, pair.getPrivate());

        System.out.println("decryptedText=" + decryptedText);
    }
}