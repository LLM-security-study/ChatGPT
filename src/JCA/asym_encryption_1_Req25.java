import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class asym_encryption_1_Req25 {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String original = "This is a secret message";

        // Encryption
        String encrypted = encrypt(original, publicKey);
        System.out.println("Encrypted: " + encrypted);

        // Decryption
        String decrypted = decrypt(encrypted, privateKey);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), "UTF8");
    }
}