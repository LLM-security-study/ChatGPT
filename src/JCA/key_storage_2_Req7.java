import java.security.KeyStore;
import java.io.FileOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req7 {
    public static void main(String[] args) {
        try {
            // Access token to be saved
            String accessToken = "your_access_token";

            // Create a new KeyStore
            KeyStore ks = KeyStore.getInstance("JCEKS");

            // Load the keystore. Since it's new, provide null as the input stream and the password.
            ks.load(null, null);

            // Convert the access token to Secret Key
            SecretKeySpec sks = new SecretKeySpec(accessToken.getBytes(), "RAW");

            // Create a KeyStore.ProtectionParameter object 
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection("keystore_password".toCharArray());

            // Create a KeyStore.SecretKeyEntry to wrap the secret key
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(sks);

            // Add the SecretKeyEntry to the KeyStore
            ks.setEntry("alias", skEntry, protParam);

            // Create a FileOutputStream to save the keystore
            FileOutputStream fos = new FileOutputStream("keystore.jks");

            // Save the KeyStore to the FileOutputStream
            ks.store(fos, "keystore_password".toCharArray());

            // Close the FileOutputStream
            fos.close();

            System.out.println("The access token was stored successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}