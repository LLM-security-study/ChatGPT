import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_2_Req12 {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Get a cipher instance
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Initialize the cipher
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encrypt the data
        String text = "Encrypt this text!";
        byte[] cipherText = cipher.doFinal(text.getBytes());
        System.out.println("Cipher Text: " + new String(cipherText));

        // Initialize the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        // Decrypt the cipher text
        byte[] decipheredText = cipher.doFinal(cipherText);
        System.out.println("Deciphered Text: " + new String(decipheredText));
    }
}