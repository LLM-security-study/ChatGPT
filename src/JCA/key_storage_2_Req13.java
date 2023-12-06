import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req13 {
    private static final String KEYSTORE_PATH = "keystore.jks";
    private static final String KEYSTORE_PASSWORD = "myKeystorePassword";
    private static final String SECRET_KEY_ALIAS = "myAccessToken";
    private static final String SECRET_KEY_PASSWORD = "myAccessTokenPassword";

    public static void main(String[] args) {
        try {
            SecretKey secretKey = createSecretKey();
            storeSecretKeyToKeystore(secretKey);

            System.out.println("Access token is stored in the keystore file successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey createSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    private static void storeSecretKeyToKeystore(SecretKey secretKey) throws Exception {
        KeyStore.ProtectionParameter protParam = new PasswordProtection(SECRET_KEY_PASSWORD.toCharArray());

        SecretKeyEntry skEntry = new SecretKeyEntry(secretKey);
        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, KEYSTORE_PASSWORD.toCharArray());
        ks.setEntry(SECRET_KEY_ALIAS, skEntry, protParam);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(KEYSTORE_PATH);
            ks.store(fos, KEYSTORE_PASSWORD.toCharArray());
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}