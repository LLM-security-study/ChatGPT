import java.security.*;
import javax.crypto.*;

public class asym_encryption_3_Req20 {
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        // Original message 
        String msg = "Hello, World!";
        System.out.println("Original Message : " + msg);

        // Encrypt message
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMsg = cipher.doFinal(msg.getBytes());
        System.out.println("Encrypted Message : " + new String(encryptedMsg));

        // Decrypt message
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMsg = cipher.doFinal(encryptedMsg);
        System.out.println("Decrypted Message : " + new String(decryptedMsg));
    }
}