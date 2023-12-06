import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;

public class key_storage_2_Req4 {

    public static void main(String[] args) {
        String alias = "access_token";     // Entry name you want to access in KeyStore
        char[] password = "password".toCharArray();  // KeyStore password
        String token = "myAccessToken";     // Access token value you want to save

        try {
            // Create a KeyStore instance with default type
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            // Load the keystore
            keyStore.load(null, password);

            // Store token as a secret key entry
            KeyStore.ProtectionParameter protParam =
                    new KeyStore.PasswordProtection(password);
            // Specify the details
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(
                    new javax.crypto.spec.SecretKeySpec(token.getBytes(), "RAW"));
            // Set the entry
            keyStore.setEntry(alias, skEntry, protParam);

            // Store the keystore to disk
            try (FileOutputStream fos = new FileOutputStream("myKeyStore.ks")) {
                keyStore.store(fos, password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}