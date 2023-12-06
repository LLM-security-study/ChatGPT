import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_2_Req11 {

    private static final String RSA = "RSA";

    public static void main(String[] args) throws Exception {

        // Step 1: Generate Key Pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Step 2: Get the Public and Private Keys from the Key Pair
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Original message which we want to encrypt
        String message = "Hello World!";

        // Step 3: Encryption
        byte[] encryptedMessage = encryptMessage(message, publicKey);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));
        
        // Step 4: Decryption 
        String decryptedMessage = decryptMessage(encryptedMessage, privateKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static byte[] encryptMessage(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    private static String decryptMessage(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }
}