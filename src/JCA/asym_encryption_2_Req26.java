import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_2_Req26 {

    private static final String RSA = "RSA";

    // Generates Public and Private keys
    public static KeyPair genKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

   // Encrypt data
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(RSA);
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes("UTF-8"));
    }

   // Decrypt data
    public static String decrypt(byte[] bytes, PrivateKey privateKey) throws Exception {
        Cipher decriptCipher = Cipher.getInstance(RSA);
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        KeyPair pair = genKeyPair();

        // Encryption
        byte[] secret = encrypt("This is a secret message", pair.getPublic());

        // Decryption
        String decrypted = decrypt(secret, pair.getPrivate());

        System.out.println("Decrypted message: " + decrypted);
    }
}