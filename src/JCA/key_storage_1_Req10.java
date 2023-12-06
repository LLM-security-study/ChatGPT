import java.security.KeyStore;
import java.security.KeyStoreException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req10 {
    public static void main(String[] args) {
        try {
            // Initializing Key Store
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);

            // Key generation
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();

            // Storing key
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            keyStore.setEntry("mySecretKey", skEntry, new KeyStore.PasswordProtection("password".toCharArray()));

            // Save the key store
            try (FileOutputStream fos = new FileOutputStream("keyStoreName.keystore")) {
                keyStore.store(fos, "password".toCharArray());
            }

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }
}