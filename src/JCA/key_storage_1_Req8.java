import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req8 {
    public static void main(String[] args) throws Exception {
        
        // create a key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // create a keystore of type JCEKS
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // keystore password
        char[] keyStorePassword = "pass".toCharArray();

        // load the keystore. since it's a new one pass null as the InputStream
        keyStore.load(null, keyStorePassword);

        // SecretKeyEntry to hold the secret key
        SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter entryPassword = 
        new KeyStore.PasswordProtection("keypass".toCharArray());

        // set the key entry in the keystore
        keyStore.setEntry("secretKeyAlias", secretKeyEntry, entryPassword);

        // store the keystore
        keyStore.store(new FileOutputStream("myKeystore.jck"), keyStorePassword);
        
        System.out.println("The cryptographic key is stored in the keystore file successfully.");
    }
}