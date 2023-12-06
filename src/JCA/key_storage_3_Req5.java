import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req5 {
    public static void main(String[] args) {
        try {
            // Load the certificate
            InputStream inStream = new FileInputStream("your_certificate.cer");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(inStream);
            inStream.close();

            // Store the certificate
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, null);
            keyStore.setCertificateEntry("mycert", cert);

            // Save the keystore
            keyStore.store(new FileOutputStream("mykeystore.jks"), "mypassword".toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}