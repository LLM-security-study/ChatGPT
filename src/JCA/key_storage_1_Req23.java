import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;

import javax.crypto.spec.SecretKeySpec;

public class key_storage_1_Req23 {
    private static final String KEYSTORE_TYPE = "JCEKS"; // Type of the keystore
    private static final String SECRET_KEY_ALGORITHM = "AES"; // Algorithm used to generate the key
    private static final String KEY_ALIAS = "myKey"; // Alias for the key in keystore
    private static final char[] KEYSTORE_PASSWORD = "password".toCharArray(); // Replace with your own
    private static final char[] KEY_PASSWORD = "mySecretKey".toCharArray(); // Replace with your own

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
        ks.load(null, KEYSTORE_PASSWORD);

        Key secretKey = new SecretKeySpec(new byte[16], SECRET_KEY_ALGORITHM);
        KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(KEY_PASSWORD);

        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry((javax.crypto.SecretKey) secretKey);
        ks.setEntry(KEY_ALIAS, skEntry, protParam);

        try (FileOutputStream fos = new FileOutputStream("keystore.ks")) {
            ks.store(fos, KEYSTORE_PASSWORD);
        }
    }
}