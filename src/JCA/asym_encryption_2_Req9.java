import java.security.*;
import javax.crypto.*;

public class asym_encryption_2_Req9 {

    public static void main(String[] args) throws Exception {

        // Generating RSA keys with key size 1024
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Input data to be encrypted
        String inputData = "Hello World!";

        // Encrypting data
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = encryptCipher.doFinal(inputData.getBytes());

        // Converting encrypted data bytes to hexadecimal string for displaying
        StringBuffer hexadecimalEncData = new StringBuffer();
        for (int i = 0; i < encryptedData.length; i++) {
            hexadecimalEncData.append(Integer.toString((encryptedData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Encrypted Data: " + hexadecimalEncData.toString());

        // Decrypting data
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = decryptCipher.doFinal(encryptedData);

        System.out.println("Decrypted Data: " + new String(decryptedData));
    }
}