import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class key_storage_2_Req17 {
    public static void main(String[] args) {
        
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            // initialize a new keystore
            char[] password = "password".toCharArray();
            keyStore.load(null, password);

            // store the access token as a secret key
            KeyStore.SecretKeyEntry secretKeyEntry =
                    new KeyStore.SecretKeyEntry(
                            new javax.crypto.spec.SecretKeySpec("accessToken".getBytes(),"RAW")); // Replace "accessToken" with your access token

            KeyStore.ProtectionParameter entryPassword =
                    new KeyStore.PasswordProtection(password);

            keyStore.setEntry("alias", secretKeyEntry, entryPassword); 

            // store the keystore in a file
            FileOutputStream fos = new FileOutputStream("keystore.jks");
            keyStore.store(fos, password);
            fos.close();
            
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }
}