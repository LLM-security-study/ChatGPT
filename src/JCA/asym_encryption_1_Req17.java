import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req17 {

    private static final String ALGORITHM = "RSA";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public asym_encryption_1_Req17() throws Exception {
        generateKeys();
    }

    public void generateKeys() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024); // You can use different sizes such as 2048, 4096, etc. 

        KeyPair pair = keyGen.generateKeyPair();
        this.publicKey = pair.getPublic();
        this.privateKey = pair.getPrivate();
    }

    public byte[] encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return encryptedBytes;
    }

    public String decrypt(byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        asym_encryption_1_Req17 mainObject = new asym_encryption_1_Req17();
        
        String originalMessage = "This is a secret message.";
        System.out.println("Original Message: " + originalMessage);

        byte[] encryptedMessage = mainObject.encrypt(originalMessage);
        System.out.println("Encrypted Message: " + new String(encryptedMessage));

        String decryptedMessage = mainObject.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}