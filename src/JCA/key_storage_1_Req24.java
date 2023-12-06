import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.io.FileOutputStream;

public class key_storage_1_Req24 {

    public static void main(String[] args) throws Exception {
        // Generate a secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // Create a keystore of type JKS
        KeyStore keyStore = KeyStore.getInstance("JKS");

        // Initially, keystore doesn't have any keys
        keyStore.load(null, null);

        // Create a protection parameter using the password "password" for the keystore
        PasswordProtection passProt = new PasswordProtection("password".toCharArray());

        // Create a secret key entry to add to the keystore
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
        
        // Set the entry alias as "mySecretKey"
        keyStore.setEntry("mySecretKey", secretKeyEntry, passProt);

        // Store the keystore in a file named "keystore.jks"
        try (FileOutputStream fos = new FileOutputStream("keystore.jks")) {
            keyStore.store(fos, "password".toCharArray());
        }
    }
}