import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req6 {
    public static void main(String[] args) throws Exception {
        final String KEYSTORE_FILE = "keystore.jks";
        final String KEY_ALIAS = "access_token";
        final char[] KS_PASSWORD = "keyPassword".toCharArray();
        final char[] KEY_PASSWORD = "keyPassword".toCharArray();

        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, KS_PASSWORD);

        SecretKey key = generateKey();
        storeKey(ks, KEY_ALIAS, key, KEY_PASSWORD);

        try (FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE)) {
            ks.store(fos, KS_PASSWORD);
        }
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); 
        return keyGen.generateKey();
    }

    private static void storeKey(KeyStore ks, String alias, SecretKey key, char[] password) 
            throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(key);
        PasswordProtection pp = new KeyStore.PasswordProtection(password);
        ks.setEntry(alias, skEntry, pp);
    }
}