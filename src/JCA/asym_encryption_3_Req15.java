import java.security.*;
import javax.crypto.Cipher;

public class asym_encryption_3_Req15 {

    private Cipher cipher;

    public asym_encryption_3_Req15() throws Exception {
        this.cipher = Cipher.getInstance("RSA");
    }

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048, new SecureRandom());
        return gen.generateKeyPair();
    }

    public byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    public String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }

    public static void main(String[] args) {
        try {
            asym_encryption_3_Req15 app = new asym_encryption_3_Req15();
            
            KeyPair keyPair = app.generateKeyPair();
            String message = "This is the secret message";

            // Encrypt the message
            byte[] cipherText = app.encrypt(message, keyPair.getPublic());

            // Decrypt the message
            String decryptedMessage = app.decrypt(cipherText, keyPair.getPrivate());

            System.out.println("Original message: " + message);
            System.out.println("Decrypted message: " + decryptedMessage);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

}