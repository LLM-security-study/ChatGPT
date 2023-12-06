import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

public class key_storage_2_Req2 {
    public static void main(String[] args) throws Exception {
        // our access token as a char[]
        String tokenStr = "YourAccessToken";
        char[] token = tokenStr.toCharArray();

        // generate secret key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");
        KeySpec spec = new PBEKeySpec(token);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);

        // create and save keystore
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(null, null);
        keyStore.setEntry("myAlias", skEntry, new KeyStore.PasswordProtection("myPassword".toCharArray()));

        try (FileOutputStream fos = new FileOutputStream("mykeystore.ks")) {
            keyStore.store(fos, "myPassword".toCharArray());
        }
    }
}