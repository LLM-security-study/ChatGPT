import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asym_encryption_3_Req7 {
    private static final String ALGORITHM = "RSA";

    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cipherText;
    }

    public static String decrypt(byte[] text, PrivateKey key) {
        byte[] decryptedText = null;
        
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptedText = cipher.doFinal(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(decryptedText);
    }

    public static void main(String args[]) {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);

            final KeyPair key = keyGen.generateKeyPair();

            String text = "This is a test message.";

            byte[] cipherText = encrypt(text, key.getPublic());

            String decryptedText = decrypt(cipherText, key.getPrivate());

            System.out.println("Original  : " + text);
            System.out.println("Encrypted : " +new String(cipherText,"UTF8"));
            System.out.println("Decrypted : " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}