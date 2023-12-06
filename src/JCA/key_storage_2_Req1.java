import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;

public class key_storage_2_Req1 {
    public static void main(String[] args) throws Exception {
        // encoded secret key (access token)
        byte[] encodedSecretKey = "access_token".getBytes();

        // create a secret key
        SecretKey secretKey = new SecretKeySpec(encodedSecretKey, "RAW");

        // create keystore instance of JCEKS type
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // load the keystore
        char[] keystorePassword = "changeit".toCharArray();
        keyStore.load(null, keystorePassword);

        // set the secret key entry in the keystore
        PasswordProtection keyPassword = new PasswordProtection("changeit".toCharArray());
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
        keyStore.setEntry("access_token", secretKeyEntry, keyPassword);

        // store the keystore
        FileOutputStream fos = new FileOutputStream("keystore.jceks");
        keyStore.store(fos, keystorePassword);
        fos.close();
    }
}