import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.*;

public class key_storage_1_Req20 {

    public static void main(String[] args) throws Exception {
        
        // Generating a key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // Storing the key in a keystore
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection("password".toCharArray());

        KeyStore.SecretKeyEntry skEntry =
                new KeyStore.SecretKeyEntry(secretKey);
        KeyStore ks = KeyStore.getInstance("JCEKS");

        ks.load(null, "password".toCharArray());
        ks.setEntry("secretKeyAlias", skEntry, protParam);

        // Saving the keystore to a file
        try (FileOutputStream fos = new FileOutputStream("newKeyStoreFileName.jceks")) {
            ks.store(fos, "password".toCharArray());
        }
    }
}