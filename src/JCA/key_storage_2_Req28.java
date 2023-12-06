import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req28 {

    public static void main(String[] args) {
        try {
            // Generating random 256-bit long secret key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();

            // Saving secret key to a keystore
            String keystoreFile = "myKeystore.jks";
            char[] password = "storepswd".toCharArray();
            String alias = "keyalias";
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null); // Initializing an empty Keystore
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);
            keyStore.setEntry(alias, skEntry, protParam);

            // Writing the Keystore to a file
            try (FileOutputStream fos = new FileOutputStream(keystoreFile)) {
                keyStore.store(fos, password);
            }
        } catch (NoSuchAlgorithmException | KeyStoreException | IOException | CertificateException e) {
            e.printStackTrace();
        }
    }
}