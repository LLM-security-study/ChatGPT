import java.security.Key;
import java.security.KeyStore;
import java.io.FileOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req15 {

    public static void main(String[] args)  throws Exception{
        String keystoreFile = "myKeyStore.keystore";
        String keystorePassword = "mypassword";
        String alias = "myAlias";

        byte[] accessToken = "myaccesstoken".getBytes();    // Byte representation of your access token. You need to replace 'myaccesstoken' with your actual token string.
        Key key = new SecretKeySpec(accessToken, "AES");

        KeyStore keyStore = KeyStore.getInstance("JCEKS");  // Creating a KeyStore object
        keyStore.load(null, keystorePassword.toCharArray());  // Initializing the keystore

        KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(keystorePassword.toCharArray());

        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry((javax.crypto.SecretKey) key);
        keyStore.setEntry(alias, skEntry, protParam);  // Storing the key in the keystore

        try (FileOutputStream fos = new FileOutputStream(keystoreFile)) { // Saving the keystore in a file
            keyStore.store(fos, keystorePassword.toCharArray());
        }
        System.out.println("Access token stored in the keystore file successfully");
    }
}