import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req20 {
    public static void main(String[] args) {
        try {
            // initialize KeyStore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // load the existing keystore file with the given password if it exists, or create a new one
            keyStore.load(null, null);

            // Generate secret key for the access token
            byte[] tokenBytes = "your_token".getBytes("UTF-8");
            SecretKeySpec secretKey = new SecretKeySpec(tokenBytes, "AES");

            // Store secret key to keystore
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.ProtectionParameter entryPassword = 
            new KeyStore.PasswordProtection("password".toCharArray());
            keyStore.setEntry("alias", secretKeyEntry, entryPassword);

            // save the keystore to a file
            try (FileOutputStream fileOutputStream = new FileOutputStream("keystore.jks")) {
                keyStore.store(fileOutputStream, "password".toCharArray());
            }
            System.out.println("Access token stored successfully in keystore file.");

        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | InvalidKeySpecException e) {
            System.err.println("Error while dealing with keystore: " + e.getMessage());
            e.printStackTrace();
        }
    }
}