import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;

public class key_storage_2_Req27 {
    public static void main(String[] args) {
        try {
            // Create and load default keystore
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] password = "new_password".toCharArray();
            ks.load(new FileInputStream(".keystore"), password);

            // Write access token as a password-protected entry
            String accessToken = "your_access_token";
            KeyStore.PasswordProtection protectParam = new KeyStore.PasswordProtection(password);
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(new javax.crypto.spec.SecretKeySpec(accessToken.getBytes(), "RAW"));
            
            ks.setEntry("access_token", skEntry, protectParam);

            // Store away the keystore
            FileOutputStream fos = new FileOutputStream(".keystore");
            ks.store(fos, password);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}