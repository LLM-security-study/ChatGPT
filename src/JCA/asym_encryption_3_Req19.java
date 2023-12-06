import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;

public class asym_encryption_3_Req19 {

    static String plainText = "This is the text to be encrypted";

    public static void main(String[] args) throws Exception {
        // Generate the public/private key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Original Text: \n" + plainText);

        // Encrypt the plaintext using the public key
        System.out.println("\nEncrypted Text: \n" + encrypt(plainText, publicKey));

        // Decrypt the ciphertext using the private key
        System.out.println("\nDecrypted Text: \n" + decrypt(encrypt(plainText, publicKey), privateKey));
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

        return new String(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = cipherText.getBytes();

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }
}