import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class key_storage_1_Req29 {
    public static void main(String[] args) {

        try {
            // generating a key pair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048, new SecureRandom());
            KeyPair pair = keyPairGen.generateKeyPair(); 

            // contains private key
            PrivateKey privKey = pair.getPrivate();
            
            // contains public key
            PublicKey publicKey = pair.getPublic(); 
            
            // Create a keystore of type JKS
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, null);
            
            // define the password protection for the keystore
            PasswordProtection password = new PasswordProtection("keystorePassword".toCharArray());
            
            // Set the keyed alias entry
            KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(privKey, new java.security.cert.Certificate[]{});
            keyStore.setEntry("myKey", privateKeyEntry, password);
            
            // Store away the keystore in a file
            try (FileOutputStream fos = new FileOutputStream("keystore.jks")) {
                keyStore.store(fos, "keystorePassword".toCharArray());
            }
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.toString());
            e.printStackTrace();
        }
    }
}