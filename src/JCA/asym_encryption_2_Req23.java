import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req23 {

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = generateKeyPair();
        PublicKey pubkey = keyPair.getPublic();
        PrivateKey privkey = keyPair.getPrivate();

        String msg = "Hello, world!";

        byte[] encrypted = encrypt(pubkey, msg);
        String decrypted = decrypt(privkey, encrypted);

        System.out.println("Original Message: " + msg);
        System.out.println("Decrypted Message: " + decrypted);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }

    public static byte[] encrypt(PublicKey key, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(PrivateKey key, byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] bytes = cipher.doFinal(encryptedMessage);

        return new String(bytes);
    }
}