import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req16 {
    public static final String KEYSTORE_FILE = "myKeystore.jceks";
    public static final String KEYSTORE_PASSWORD = "secured_password";
    public static final String KEY_ALIAS = "mySecretKey";
    public static final String KEY_PASSWORD = "key_password";

    public static void main(String[] args) throws Exception {
        SecretKey secretKey = createSecretKey();
        storeSecretKeyToKeystore(secretKey);
    }
    
    public static SecretKey createSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        int keyBitSize = 256;
        keyGenerator.init(keyBitSize, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static void storeSecretKeyToKeystore(SecretKey secretKey) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(null, null);
        KeyStore.ProtectionParameter protectionParam = 
                new KeyStore.PasswordProtection(KEY_PASSWORD.toCharArray());
        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
        keyStore.setEntry(KEY_ALIAS, skEntry, protectionParam);
        try (FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE)) {
            keyStore.store(fos, KEYSTORE_PASSWORD.toCharArray());
        }
    }
}