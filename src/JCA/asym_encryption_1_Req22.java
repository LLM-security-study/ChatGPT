import java.security.*;
import javax.crypto.*;

public class asym_encryption_1_Req22
{
    public static void main(String[] args) throws Exception
    {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        String message = "This is a secret message";
        byte[] encrypted = encrypt(privateKey, message);
        System.out.println(new String(encrypted)); 

        // decrypt the message
        byte[] secret = decrypt(pubKey, encrypted);                                 
        System.out.println(new String(secret));     
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }

    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}