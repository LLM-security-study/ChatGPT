import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.SecretKey;

public class key_storage_1_Req1 {
    public static void main(String[] args) throws Exception {
        // creating a new KeyStore
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // loading the KeyStore
        keyStore.load(null, null); // create an empty keystore

        // generating the cryptographic key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); //setting key size
        SecretKey secretKey = keyGenerator.generateKey();

        // creating a SecretKeyEntry to protect the SecretKey
        SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);

        // setting a password for the key
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection("password".toCharArray());

        // setting the key entry into the keystore
        keyStore.setEntry("keyAlias", secretKeyEntry, protectionParameter);

        // saving the keystore into the file system
        try (FileOutputStream fileOutputStream = new FileOutputStream("keystore.jceks")) {
            keyStore.store(fileOutputStream, "keystorePassword".toCharArray());
        }

        System.out.println("Cryptographic key stored in the keystore successfully!");        
    }
}