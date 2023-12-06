import java.io.FileOutputStream;
import java.security.KeyStore;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

public class key_storage_1_Req25 {

    public static void main(String[] args) {
        try {
            // 1. Set up
            String keystoreFile = "nameofyourkeystoreFile.jceks";
            String keystorePassword = "keystorePassword";
            String alias = "alias";
            String keyPassword = "keyPassword";
            String keyAlgorithm = "HmacSHA256";

            // 2. Generate SecretKey
            KeyGenerator keyGen = KeyGenerator.getInstance(keyAlgorithm);
            SecretKey key = keyGen.generateKey();

            // 3. Create KeyStore and set the keyStore & password
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, keystorePassword.toCharArray());

            // 4. Store SecretKey in KeyStore
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(key);
            keyStore.setEntry(alias, skEntry, new KeyStore.PasswordProtection(keyPassword.toCharArray()));

            // 5. Store KeyStore in a file
            java.io.FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(keystoreFile);
                keyStore.store(fos, keystorePassword.toCharArray());
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
            System.out.println("Success! The cryptographic key is stored in keystore file.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}