import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import javax.crypto.KeyGenerator;

public class key_storage_2_Req22 {

    public static void main(String[] args) {
        String keystoreFilePath = "keystore.jks";
        String keystorePassword = "password";
        String alias = "accessToken";
        
        try {
            // Generate a Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            Key key = keyGenerator.generateKey();

            // Store Key in a new Keystore
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(null, keystorePassword.toCharArray());
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key);
            KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(keystorePassword.toCharArray());
            keystore.setEntry(alias, secretKeyEntry, passwordProtection);
            
            // Save Keystore to File
            FileOutputStream fos = new FileOutputStream(keystoreFilePath);
            keystore.store(fos, keystorePassword.toCharArray());
            fos.close();
            
            System.out.println("Keystore has been created and the key has been stored successfully!");
        } catch (Exception e) {
            System.out.println("Error Storing Key: " + e.getMessage());
            e.printStackTrace();
        }
    }
}