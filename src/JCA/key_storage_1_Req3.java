import java.security.*;
import java.io.*;
import java.security.KeyStore;
import java.security.cert.*;

public class key_storage_1_Req3 {

    public static void main(String[] args) {
        try {
            // Step 1: Create the keys
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(1024, new SecureRandom());
            KeyPair pair = keyGenerator.generateKeyPair();
            PublicKey pubKey = pair.getPublic();
            PrivateKey privKey = pair.getPrivate();

            // Step 2: Create a keystore and store the keys
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null); // Initialize an empty keystore

            // Step 3: Add the private key to the keystore
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection("password".toCharArray());
            KeyStore.PrivateKeyEntry privKeyEntry = new KeyStore.PrivateKeyEntry(privKey, new Certificate[0]);
            keyStore.setEntry("keyAlias", privKeyEntry, protParam);

            // Step 4: Write the keystore to a file
            FileOutputStream fos = new FileOutputStream("myKeystore.ks");
            keyStore.store(fos, "password".toCharArray());
            fos.close();
            System.out.println("Keystore created and keys are stored successfully.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            ex.printStackTrace();
        }
    }
}