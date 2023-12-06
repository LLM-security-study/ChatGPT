import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req12 {

    public static void main(String[] args) {
        try {
            // Create a key and store it in a new keystore
            byte[] keyValue = "mySecretKey".getBytes("UTF-8"); // replace 'mySecretKey' with your desired secret key
            Key key = new SecretKeySpec(keyValue, "AES");
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);
            KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection("password".toCharArray()); 
            
            KeyStore.SecretKeyEntry keyStoreSKE = new KeyStore.SecretKeyEntry((javax.crypto.SecretKey) key);
            keyStore.setEntry("myAlias", keyStoreSKE, keyStorePP); // replace 'myAlias' with your alias

            // Save the keystore to a file
            try (FileOutputStream fos = new FileOutputStream("myKeystore.jceks")) {
                keyStore.store(fos, "password".toCharArray());
            }
            
            System.out.println("Keystore file created successfully.");
        } catch (Exception ex) {
            System.out.println("Error creating keystore: " + ex);
        }
    }
}