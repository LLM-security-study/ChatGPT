import java.io.FileOutputStream;
import java.security.*;
import java.security.cert.CertificateException;

public class key_storage_1_Req5 {

    public static void main(String[] args) {
        try {
            // generate key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // create a keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            // initialize keystore
            keyStore.load(null, null);

            // store the keypair in the keystore
            String alias = "mykey";
            String passphrase = "mypassphrase";
            KeyStore.PasswordProtection password = new KeyStore.PasswordProtection(passphrase.toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = new KeyStore.PrivateKeyEntry(keyPair.getPrivate(), new java.security.cert.Certificate[]{});
            keyStore.setEntry(alias, pkEntry, password);

            // write the keystore to a file
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("keystore.jks");
                keyStore.store(fos, passphrase.toCharArray());
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}