import java.security.KeyStore;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStore.PasswordProtection;

public class key_storage_2_Req9 {
    public static void main(String[] args) {
        try {
            // Generate a Secret Key to be our Access Token
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();

            // Create our Secret Key Entry
            SecretKeyEntry secret = new SecretKeyEntry(secretKey);
            PasswordProtection pass = new PasswordProtection("password".toCharArray());

            // Load or Create our KeyStore
            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(null, "keystorePassword".toCharArray());

            // Set our Entry
            ks.setEntry("accessToken", secret, pass);

            // Store the Keystore to a File
            FileOutputStream fos = new FileOutputStream("accessTokenStore.jceks");
            ks.store(fos, "keystorePassword".toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}