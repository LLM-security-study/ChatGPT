import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_2_Req24 {

    public static void main(String[] args) {
        try {
            // Create a new secret key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();

            // Create a new keystore file
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);

            // Store the secret key
            KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection("keystore-password".toCharArray());
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            keyStore.setEntry("access-token", skEntry, keyStorePP);

            // Save the keystore file
            try (FileOutputStream fos = new FileOutputStream("keystore.jceks")) {
                keyStore.store(fos, "keystore-password".toCharArray());
            }

            System.out.println("Access token successfully stored in 'keystore.jceks'.");

        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
        }

    }
}    