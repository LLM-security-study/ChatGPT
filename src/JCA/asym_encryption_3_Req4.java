import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class asym_encryption_3_Req4 {

    static public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    static public String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    static public String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes));
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        String plainText = "Hello, world!";
        String cipherText = encrypt(plainText, keyPair.getPublic());

        System.out.println("Original message: " + plainText);
        System.out.println("Encrypted message: " + cipherText);
        System.out.println("Decrypted message: " + decrypt(cipherText, keyPair.getPrivate()));
    }
}