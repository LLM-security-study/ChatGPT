import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req18 {

    public static void main(String[] args) {
        try {
            // Initialize KeyStore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // Create (if not existing) or Load (if existing) Keystore
            char[] keystorePassword = "mykeystorepass".toCharArray();
            java.io.FileInputStream fis = null;
            try {
                fis = new java.io.FileInputStream("mykeystore.ks");
                keyStore.load(fis, keystorePassword);
            } catch (java.io.FileNotFoundException e) {
                // If not found, just start a new keystore
                keyStore.load(null, keystorePassword);
            } finally {
                if(fis!=null)
                    fis.close();
            }

            // Generate a SecretKey for our token. This can be replaced by any SecretKey object.
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();

            // Create the access token
            String myToken = "ACCESS_TOKEN_GOES_HERE";

            // Associate the access token to the secret key
            SecretKeyEntry ske = new SecretKeyEntry(secretKey);
            ProtectionParameter protParam = new KeyStore.PasswordProtection(myToken.toCharArray());

            // Set the access token in the keystore
            keyStore.setEntry("myAlias", ske, protParam);

            // Store the keystore
            java.io.FileOutputStream fos = null;
            try {
                fos = new java.io.FileOutputStream("mykeystore.ks");
                keyStore.store(fos, keystorePassword);
            } finally {
                if(fos!=null)
                    fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}