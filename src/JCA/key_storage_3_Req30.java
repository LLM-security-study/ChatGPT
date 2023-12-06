import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class key_storage_3_Req30 {

    public static void main(String[] args) throws Exception{
        // load the keystore
        char[] keystorePassword = "password".toCharArray(); // your keystore password
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        java.io.InputStream in = new FileInputStream("keystore.jks");  // your keystore file path
        keystore.load(in, keystorePassword);
        in.close();

        // store the certificate
        char[] certPassword = "password".toCharArray(); // your certificate password
        Key key = keystore.getKey("alias", certPassword); // "alias" is alias of certificate
        Certificate[] chain = keystore.getCertificateChain("alias");
        keystore.setKeyEntry("alias", key, certPassword, chain);

        // write the keytore to filesystem
        FileOutputStream fos = null;
        try {
            fos = new java.io.FileOutputStream("newKeystore.jks");
            keystore.store(fos, keystorePassword);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}