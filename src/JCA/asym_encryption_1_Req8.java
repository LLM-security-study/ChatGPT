import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req8 {

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024, new SecureRandom());
        return generator.generateKeyPair();
    }

    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] bytes, PrivateKey privateKey) throws Exception {
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        // The original data
        String text = "This is a secret message";

        System.out.println("Original Text: " + text);

        // Encrypt using the public key
        byte[] secret = encrypt(text, keyPair.getPublic());

        System.out.println("Encrypted message: " + new String(secret, "UTF-8") );

        // Decrypt using the private key
        String decryptedSecret = decrypt(secret, keyPair.getPrivate());

        System.out.println("Decrypted Text: " + decryptedSecret);
    }

}