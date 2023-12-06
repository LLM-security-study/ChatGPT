import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_1_Req26 {
    public static void main(String[] args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // for example
        SecretKey secretKey = keyGen.generateKey();

        KeyStore.ProtectionParameter protParam =
            new KeyStore.PasswordProtection("password".toCharArray());

        KeyStore.SecretKeyEntry skEntry =
            new SecretKeyEntry(secretKey);
        KeyStore ks = KeyStore.getInstance("JCEKS");

        ks.load(null, "password".toCharArray());
        ks.setEntry("secretKeyAlias", skEntry, protParam);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("newKeyStoreFileName.jceks");
            ks.store(fos, "password".toCharArray());
        } finally {
           if (fos != null) {
              fos.close();
           }
        }
    }
}