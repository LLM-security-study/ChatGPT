import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req16 {
    public static void main(String[] args) {
        try {
            // URL for SSL Certificate
            String certUrl = "https://your_cert_url";

            // Setup SSL certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(new URL(certUrl).openStream());

            // Load traditional Java keyStore
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream("myKeyStore"), "myKeyStorePassword".toCharArray());

            // Save the certificate into the keystore
            ks.setCertificateEntry("myCertAlias", cert);

            // Write the new keystore contents to a new file
            ks.store(new FileOutputStream("myNewKeyStore"), "myNewKeyStorePassword".toCharArray());

            System.out.println("Certificate is stored successfully!");
        } catch (Exception e) {
            // Implementing basic error handling for illustration
            System.out.println("Failed to store the certificate: " + e.getMessage());
        }
    }
}