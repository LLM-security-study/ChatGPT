import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_1_Req18 {
    public static byte[] encrypt(String original, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] originalBytes = original.getBytes(StandardCharsets.UTF_8);
        return encryptCipher.doFinal(originalBytes);
    }

    public static String decrypt(byte[] encrypted, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] decryptedBytes = decryptCipher.doFinal(encrypted);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        // generate key pair
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
    
        KeyPair pair = generator.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        // encrypt the plaintext
        String original = "plain text";
        byte[] encrypted = encrypt(original, publicKey);
        System.out.println("encrypted: " + new String(encrypted, StandardCharsets.UTF_8));

        // decrypt the ciphertext
        String decrypt = decrypt(encrypted, privateKey);
        System.out.println("decrypted: " + decrypt);
    }
}