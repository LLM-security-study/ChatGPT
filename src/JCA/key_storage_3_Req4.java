import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req4 {

    // location of the keystore file
    private static final String KEYSTORE_FILE = "/path/to/keystore";
    private static final String KEYSTORE_PASSWORD = "keyStorePassword";

    // certificate file to be stored
    private static final String CERTIFICATE_FILE = "/path/to/certificate.crt";
    private static final String CERTIFICATE_ALIAS = "certificateAlias";

    public static void main(String[] args) {
        try {
            // load the keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());

            // create a certificate object
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream certStream = new FileInputStream(CERTIFICATE_FILE);
            Certificate certificate = cf.generateCertificate(certStream);

            // store the certificate
            keyStore.setCertificateEntry(CERTIFICATE_ALIAS, certificate);

            // save the new keystore contents
            FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE);
            keyStore.store(fos, KEYSTORE_PASSWORD.toCharArray());

            System.out.println("Certificate stored successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}