import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req5 {

    public static void main(String[] args) {
        try {
            // create a keystore of type JCEKS
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // load the keystore
            char[] keystorePassword = "password".toCharArray();
            keyStore.load(null, keystorePassword);

            // create a secret key
            String myAccessToken = "myAccessToken123";
            SecretKey secretKey = new SecretKeySpec(myAccessToken.getBytes(), "RAW");

            // store the secret key
            KeyStore.SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
            KeyStore.ProtectionParameter entryPassword = 
                new KeyStore.PasswordProtection(keystorePassword);
            keyStore.setEntry("MyAccessToken", secretKeyEntry, entryPassword);

            // store the keystore
            keyStore.store(new FileOutputStream("MyKeyStore.jks"), keystorePassword);

            System.out.println("Access token has been stored in the keystore successfully!");

        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

}