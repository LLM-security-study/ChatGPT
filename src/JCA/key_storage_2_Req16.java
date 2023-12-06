 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStore.PasswordProtection;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
   
public class key_storage_2_Req16 {

    public static void main(String[] args) {
        try {
            // The access token
            String accessToken = "your-access-token";
            char[] accessTokenArray = accessToken.toCharArray();
    
            // Keystore password
            char[] keyStorePassword = "your-keystore-password".toCharArray();
    
            // Key password
            char[] keyPassword = "your-key-password".toCharArray();
    
            // Create a new KeyStore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, keyStorePassword);
    
            // Create a new SecretKey
            SecretKey secretKey = new SecretKeySpec(accessTokenArray, "RAW");
    
            // Set the entry in the keystore
            SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
            ProtectionParameter protectionParameter = new PasswordProtection(keyPassword);
            keyStore.setEntry("accessToken", secretKeyEntry, protectionParameter);
    
            // Store the keystore to a file
            keyStore.store(new FileOutputStream("myAccessTokenStore.jceks"), keyStorePassword);
    
            System.out.println("Access token stored successfully.");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}