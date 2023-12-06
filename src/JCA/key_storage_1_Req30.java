import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.Key;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class key_storage_1_Req30 {
    
    public static final String KEYSTORE_FILE = "myKeyStore.jks";
    public static final String KEY_ALIAS = "myKey";
    public static final String KEYSTORE_PASSWORD = "password";
    public static final String KEY_PASSWORD = "keypassword";

    public static void main(String[] args) {

        try {
            // Create a new key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);  // Key size
            SecretKey secretKey = keyGen.generateKey();

            // Create a keystore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);  // Null load initializes an empty keystore

            // Add the new key to the keyStore
            SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.ProtectionParameter entryPassword = 
                new KeyStore.PasswordProtection(KEY_PASSWORD.toCharArray());
            keyStore.setEntry(KEY_ALIAS, secretKeyEntry, entryPassword);

            // Create a keystore file, write the keystore to the file, then close it
            FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE);
            keyStore.store(fos, KEYSTORE_PASSWORD.toCharArray());
            fos.close();

            System.out.println("The key has been successfully stored in the keystore.");

        } catch (Exception e) {
            System.out.println("Failed to create a key and store it in the keystore.");
            e.printStackTrace();
        }
    }
}