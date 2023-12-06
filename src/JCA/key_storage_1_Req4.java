import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req4 {

    public static void main(String[] args) throws Exception {

        // Start by creating a KeyGenerator.
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Generate the secret key. 
        SecretKey secretKey = keyGen.generateKey();

        // Create a KeyStore.
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // Load the keystore.
        char[] password = "password".toCharArray();
        keyStore.load(null, password);

        // Store the secret key.
        PasswordProtection keyPassword = 
                new PasswordProtection(password);
        SecretKeyEntry ske =
                new SecretKeyEntry(secretKey);
        keyStore.setEntry("keyAlias", ske, keyPassword);

        // Store the keystore.
        java.io.FileOutputStream fos =
            new java.io.FileOutputStream("mykeystore.ks");
        keyStore.store(fos, password);
        fos.close();
     }
}