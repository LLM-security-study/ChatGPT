import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class key_storage_2_Req29 {
    
    static final String KEYSTORE = "keystore.jks";
    static final String ALIAS = "alias";
    static final String PASSWORD = "password";
    static final String ACCESS_TOKEN = "yourAccessToken";

    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
      
        // create new keystore
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        // load the keystore
        ks.load(null, PASSWORD.toCharArray());

        // create a new key 
        Key key = new SecretKeySpec(ACCESS_TOKEN.getBytes(), "DES");
        
        // store the key in keystore
        KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection(PASSWORD.toCharArray());
        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry((SecretKey) key);
        ks.setEntry(ALIAS, skEntry, keyStorePP);

        // store the keystore 
        try (FileOutputStream fos = new FileOutputStream(KEYSTORE)) {
            ks.store(fos, PASSWORD.toCharArray());
        }

        System.out.println("Stored access token in keystore!");
    }
}