import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_1_Req7 {

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String msg = "Hello, World!";
        byte[] encryptedMsg = encrypt(publicKey, msg);
        System.out.println("Encrypted Message: " + new String(encryptedMsg));

        byte[] decryptedMsg = decrypt(privateKey, encryptedMsg);
        System.out.println("Decrypted Message: " + new String(decryptedMsg));
    }

    public static KeyPair buildKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return cipher.doFinal(encrypted);
    }
}