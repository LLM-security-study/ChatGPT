import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_1_Req19 {

    // function to encrypt a plain text
    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    // function to decrypt a cipher text
    public static String decrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(dectyptedText);
    }

    public static void main(String[] args) {
        try {
            // generating public and private keys
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // key size
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // the plain text we want to encrypt
            String plainText = "Text to be encrypted";

            //Encrypt the plain text using public key
            byte[] cipherText = encrypt(plainText,publicKey);

            System.out.println("cipher: " + new String(cipherText, "UTF8"));

            //Decrypt the text using private key
            String dectyptedText = decrypt(cipherText,privateKey);

            System.out.println("plain : " + dectyptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}