import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req9 {
    private static final String KEYSTORE_PATH = "keystore.jks";
    private static final String KEYSTORE_PASSWORD = "password"; 
    private static final String CERTIFICATE_PATH = "myCertificate.crt";
    private static final String CERTIFICATE_ALIAS = "alias";

    public static void main(String[] args) {
        try {
            // Create instance of KeyStore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            // Load the keystore content
            try (FileInputStream in = new FileInputStream(KEYSTORE_PATH)) {
                keyStore.load(in, KEYSTORE_PASSWORD.toCharArray());
            }

            // Load the certificate object
            Certificate cert = loadCertificate(CERTIFICATE_PATH);

            // Add the certificate
            keyStore.setCertificateEntry(CERTIFICATE_ALIAS, cert);

            // Save the keystore
            try (FileOutputStream out = new FileOutputStream(KEYSTORE_PATH)) {
                keyStore.store(out, KEYSTORE_PASSWORD.toCharArray());
            }

            System.out.println("The certificate was stored successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Certificate loadCertificate(String certPath) throws IOException {
        Certificate cert = null;
        try (FileInputStream in = new FileInputStream(certPath)) {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            cert = factory.generateCertificate(in);
        }
        return cert;
    }
}