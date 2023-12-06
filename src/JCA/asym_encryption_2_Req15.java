import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_2_Req15 {
    private static final String ALGORITHM = "RSA";

    public static byte[] encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static void main(String[] args) {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();

            String msg = "Hello, World!";
            System.out.println("Original Message: "+msg);

            byte[] encrypted = encrypt(msg, key.getPublic());

            String decrypted = decrypt(encrypted, key.getPrivate());
            System.out.println("Decrypted Message: "+decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}