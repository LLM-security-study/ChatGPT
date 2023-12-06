import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_1_Req5 {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair(); 

        String msg = "Hello, World!";
        String encMsg = encryptMessage(msg, pair.getPrivate());
        System.out.println("Encrypted message: " + encMsg);

        String decMsg = decryptMessage(encMsg, pair.getPublic());
        System.out.println("Decrypted message: " + decMsg);
    }
 
    private static String encryptMessage(String msg, PrivateKey key) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(msg.getBytes());
        return new String(java.util.Base64.getEncoder().encodeToString(encValue));
    }

    private static String decryptMessage(String msg, PublicKey key) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = java.util.Base64.getDecoder().decode(msg);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }
}