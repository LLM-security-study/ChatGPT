import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req3 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            // Load the JDK's key store
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] password = "changeit".toCharArray();

            // Load your existing keystore to append the new key. If you want to create a new keystore, then ignore this step
            fis = new FileInputStream("myFile.jks");
            keystore.load(fis, password);

            // Create a new SSL certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(new FileInputStream("mySSLCert.cer"));

            // Add the certificate
            keystore.setCertificateEntry("myAlias", cert);

            // Save the new keystore contents
            FileOutputStream out = new FileOutputStream("myFile.jks");
            keystore.store(out, password);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}