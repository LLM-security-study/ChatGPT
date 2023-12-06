import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req28 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            // Step 1: Load the certificate
            fis = new FileInputStream("mycertificate.cer");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(fis);

            // Step 2: Add the certificate to the Keystore
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);  // initialize an empty keystore
            ks.setCertificateEntry("myalias", cert);

            // Step 3: Store the Keystore to a file
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("mykeystore.jks");
                ks.store(fos, "mypassword".toCharArray());
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        } catch (CertificateException | IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}