import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req20 {
    public static void main(String[] args) {
        try {
            // Load the certificate 
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream certInputStream = new FileInputStream("path/to/certFile.crt");
            Certificate certificate = certificateFactory.generateCertificate(certInputStream);
            certInputStream.close();

            // Install the certificate into a new keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null); // Initialize an empty keystore
            keyStore.setCertificateEntry("alias", certificate);

            // Save the keystore to a file
            FileOutputStream fos = new FileOutputStream("path/to/keystore.jks");
            keyStore.store(fos, "keystorePassword".toCharArray());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}