import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_1_Req12 {
    public static void main(String[] args) {
        try {
            // Generate Secret Key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // for example
            SecretKey secretKey = keyGen.generateKey();

            // Create KeyStore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);

            // Put Key into KeyStore
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            keyStore.setEntry("mySecretKey", skEntry, 
                               new KeyStore.PasswordProtection("password".toCharArray()));

            // Write KeyStore to File
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("keystore.jceks");
                keyStore.store(fos, "password".toCharArray());
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }

            System.out.println("Key has been stored successfully in the keystore file.");

        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException 
                   | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }
}