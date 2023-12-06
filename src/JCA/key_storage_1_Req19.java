import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.Key;
import javax.crypto.KeyGenerator;

public class key_storage_1_Req19 {

    public static void main(String[] args)  throws Exception {
    
        // Generate key 
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        Key aesKey = keyGen.generateKey();
        
        // Create keystore
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(null, null);
        
        // Set key entry in keystore
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(aesKey);
        KeyStore.ProtectionParameter entryPassword = 
                new KeyStore.PasswordProtection("entryPassword".toCharArray());
        keyStore.setEntry("aesKeyAlias", secretKeyEntry, entryPassword);
        
        // Store keystore in file
        try (FileOutputStream fos = new FileOutputStream("myKeystore.jceks")) {
            keyStore.store(fos, "keystorePassword".toCharArray());
        }
    }
}