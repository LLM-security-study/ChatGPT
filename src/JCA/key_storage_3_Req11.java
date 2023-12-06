import java.security.cert.Certificate;
import java.security.KeyStore;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class key_storage_3_Req11 {

    public static void main(String[] args) {
        try {
            // Load the JDK's key store
            String keystoreFilename = "./mykeystore";
            char[] password = "password".toCharArray();
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (InputStream in = new FileInputStream(keystoreFilename)) {
                keystore.load(in, password);
            }
 
            // Load the certificate
            String alias = "alias";
            Certificate cert = null;
            try (InputStream in = new FileInputStream(keystoreFilename)) {
                cert = CertificateFactory.getInstance("X.509").generateCertificate(in);
            }
 
            // Add the certificate
            keystore.setCertificateEntry(alias, cert);
 
            // Save the new key store contents
            try (OutputStream out = new FileOutputStream(keystoreFilename)) {
                keystore.store(out, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}