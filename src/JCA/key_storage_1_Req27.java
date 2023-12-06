import java.security.KeyStore;
import java.security.Key;
import java.util.Base64;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.SecureRandom;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.FileOutputStream;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req27 {
    public static void main(String[] args) {
        try {
            // Create the KeyStore object
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // Create the key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            Key key = keyGen.generateKey();

            // Create a key pair
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();

            // Init the keystore
            keyStore.load(null, null);
            KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection("password".toCharArray());

            // Set the key entry
            SecretKeyEntry skEntry = new SecretKeyEntry((SecretKey) key);
            keyStore.setEntry("secretKeyAlias", skEntry, keyStorePP);

            // Store away the keystore
            FileOutputStream fos = new FileOutputStream("newKeyStoreFileName.jceks");
            keyStore.store(fos, "password".toCharArray());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}