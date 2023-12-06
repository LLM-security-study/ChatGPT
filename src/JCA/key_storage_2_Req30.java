import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;

public class key_storage_2_Req30 {
    public static void main(String[] args) {
        try {
            // create new keystore object
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // load the keystore
            char[] password = "keystorePassword".toCharArray();
            keyStore.load(null, password);

            // store the secret (access token in this case)
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(
                    new javax.crypto.spec.SecretKeySpec("accessToken".getBytes(), "HmacSHA256"));
            PasswordProtection keyPassword = new PasswordProtection("keyPassword".toCharArray());

            keyStore.setEntry("alias", secretKeyEntry, keyPassword);

            // save the keystore to a file
            try (FileOutputStream fos = new FileOutputStream("keystore.ks")) {
                keyStore.store(fos, password);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}