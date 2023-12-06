import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class asym_encryption_2_Req1 {
    public static void main(String[] args) throws Exception {

        // KeyPair generation
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGen.generateKeyPair(); 
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Original message
        String msg = "Hello, World!";
        System.out.println("Original Message: " + msg);

        // Encryption
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(msg.getBytes());
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedBytes));

        // Decryption
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
        System.out.println("Decrypted Message: " + new String(decryptedBytes));
    }
}