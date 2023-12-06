import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class asym_encryption_1_Req14 {

    public static void main(String[] args) {
        try {
            // generate public and private keys
            KeyPair keyPair = buildKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // encrypt the message 
            byte[] encrypted = encrypt(privateKey, "This is a classified message");     
            System.out.println(new String(encrypted, "UTF8")); 

            // decrypt the message
            byte[] secret = decrypt(publicKey, encrypted);                                 
            System.out.println(new String(secret, "UTF8"));    

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(data.getBytes("UTF8"));
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}