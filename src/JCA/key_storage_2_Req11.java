import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class key_storage_2_Req11 {
    
    public static void main(String[] args) {
        // You should replace these values with your all values.
        String tokenToStore = "YourToken";
        String ksFileName = "keystore.ks"; // Path to your keystore file
        String ksPassword = "YourKSPassword"; // Keystore password
        String alias = "YourAlias"; // The alias under you want to store the token

        try {
            // Load the keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            java.io.FileInputStream fis = null;
            try {
                fis = new java.io.FileInputStream(ksFileName);
                keyStore.load(fis, ksPassword.toCharArray());
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
            
            // Convert the token to a Key
            javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(tokenToStore.getBytes(), "AES");
      
            // Store the key in the keystore
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(key);
            keyStore.setEntry(alias, skEntry, new KeyStore.PasswordProtection(ksPassword.toCharArray()));

            // Save the keystore
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(ksFileName);
                keyStore.store(fos, ksPassword.toCharArray());
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }

            System.out.println("Token stored successfully.");

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException e) {
            // Log the exception or print the stack trace
            e.printStackTrace();
        }
    }
}