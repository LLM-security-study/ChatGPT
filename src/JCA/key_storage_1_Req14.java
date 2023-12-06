import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req14 {
    public static void main(String[] args) throws Exception {

        // create a key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();

        // save the key in a keystore
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(null, null);
        PasswordProtection keyPassword = 
               new PasswordProtection("keyPassword".toCharArray());
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
        keyStore.setEntry("secretKeyAlias", secretKeyEntry, keyPassword);
        
        try (FileOutputStream fos = new FileOutputStream("keystore.ks")) {
            keyStore.store(fos, "keystorePassword".toCharArray());
        }
        System.out.println("The secret key was successfully stored in the keystore file.");
    }
}