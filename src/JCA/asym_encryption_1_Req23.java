import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req23 {
    private static final String ALGORITHM = "RSA";
    
    // Function to encrypt string
    public byte[] encrypt(String text, PublicKey key) {
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
    
    // Function to decrypt string
    public String decrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(dectyptedText);
    }

    public static void main(String[] args) {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair keyPair = keyGen.generateKeyPair();
            asym_encryption_1_Req23 mainobj = new asym_encryption_1_Req23();

            String text = "This is a secret message";
            byte[] cipherText = mainobj.encrypt(text, keyPair.getPublic());
            String decryptedText = mainobj.decrypt(cipherText, keyPair.getPrivate());

            System.out.println("Original Text: " + text);
            System.out.println("Encrypted Text: " + new String(cipherText));
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}