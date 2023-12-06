import java.security.*;
import javax.crypto.*;
import java.util.Base64;

public class asym_encryption_2_Req14 {

    public static void main(String[] args) throws Exception {

        String plainText = "Hello world!";

        // Generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encrypt
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted String: " + encryptedString);

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted String: " + decryptedString);
    }
}