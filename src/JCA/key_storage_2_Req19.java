import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req19 {
    public static void main(String[] args) {
        try {
            String keystorePassword = "keystorePassword";
            String keyPassword = "keyPassword";
            
            // Generate secret key for token
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            
            // Create secret key entry
            SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            PasswordProtection passwordProtection = new PasswordProtection(keyPassword.toCharArray());
            
            // Create keystore and store the secret key
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, keystorePassword.toCharArray());
            keyStore.setEntry("accessToken", secretKeyEntry, passwordProtection);
            
            // Save keystore to a file
            keyStore.store(new FileOutputStream("keystore.jceks"), keystorePassword.toCharArray());

        } catch (NoSuchAlgorithmException | IOException | KeyStoreException | CertificateException | UnrecoverableEntryException ex) {
            ex.printStackTrace();
        }
    }
}