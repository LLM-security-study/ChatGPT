import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;

public class key_storage_2_Req21 {
    public static void main(String[] args) {
        try {
            // Create an instance of keystore of type JCEKS
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            
            // For a keystore that is not loaded from disk, you need to load it
            // and specify it's not been saved yet, by setting a null InputStream
            keyStore.load(null, null);
            
            // Create an access token secret
            KeyStore.PasswordProtection keySecret = 
              new KeyStore.PasswordProtection("your_token".toCharArray());

            // Set the access token in keystore
            keyStore.setEntry(
             "access_token", 
             new KeyStore.SecretKeyEntry(keySecret.getPasswordProtection()), 
             keySecret);
            
            // Save the keystore to a file
            try (FileOutputStream fos = 
                new FileOutputStream("accessToken.keystore")) {
             keyStore.store(fos, "keystore_password".toCharArray());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}