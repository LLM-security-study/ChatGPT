import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class key_storage_1_Req28 {
    
    public static void main(String[] args) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);
            
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // for example
            SecretKey secretKey = keyGen.generateKey();
           
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            keyStore.setEntry("mySecretKey", skEntry, new KeyStore.PasswordProtection("password".toCharArray()));
             
            try (FileOutputStream fos = new FileOutputStream("keystore.ks")) {
                keyStore.store(fos, "keyStorePassword".toCharArray());
            }
                       
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }
}