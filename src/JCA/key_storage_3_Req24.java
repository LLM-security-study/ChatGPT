import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req24 {

    private static final String SSL_CERT_PATH = "test.cer";
    private static final String KEYSTORE_PATH = "keystore.jks";
    private static final String KEYSTORE_PASSWORD = "password"; // Replace with your keystore password
    private static final String ALIAS = "alias"; // Replace with your alias

    public static void main(String[] args) {
        try {
            // Create a CertificateFactory
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            // Instantiate FileInputStream for the SSL certificate
            FileInputStream fis = new FileInputStream(SSL_CERT_PATH);

            // Generate a Certificate
            Certificate cert = cf.generateCertificate(fis);

            // Generate a keystore of type JKS
            KeyStore ks = KeyStore.getInstance("JKS");

            // Load the keystore
            ks.load(null, KEYSTORE_PASSWORD.toCharArray());

            // Store the certificate into the keystore
            ks.setCertificateEntry(ALIAS, cert);

            // Write the Keystore to a file
            FileOutputStream fos = new FileOutputStream(KEYSTORE_PATH);
            ks.store(fos, KEYSTORE_PASSWORD.toCharArray());

            fis.close();
            fos.close();
            System.out.println("SSL certificate has been stored in the Keystore successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}