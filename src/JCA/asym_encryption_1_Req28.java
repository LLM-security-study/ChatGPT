import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_1_Req28{
    public static void main(String[] args) throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey pubk = kp.getPublic();
        PrivateKey prk = kp.getPrivate();
        String message = "Hello, World";

        byte[] encryptedMessage = publicKeyEncryption(pubk, message);
        String decryptedMessage = privateKeyDecryption(prk, encryptedMessage);

        System.out.println("Original message: " + message);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static byte[] publicKeyEncryption(PublicKey pubk, String message) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubk);
        return cipher.doFinal(message.getBytes());
    }

    public static String privateKeyDecryption(PrivateKey prk, byte[] encryptedMessage) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, prk);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }
}