import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req1 {
    public static void main(String[] args) {
        try {
            // Load the certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            FileInputStream certInStream = new FileInputStream("CERTIFICATE_FILE_PATH");
            Certificate cert = cf.generateCertificate(certInStream);

            // Load or create a new Keystore
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream ksInStream = null;
            try {
                ksInStream = new FileInputStream("KEYSTORE_FILE_PATH");
                ks.load(ksInStream, "KEYSTORE_PASSWORD".toCharArray());
            } catch (Exception e) {
                System.out.println("Exception while loading keystore. Creating a new one.");
                ks.load(null, "KEYSTORE_PASSWORD".toCharArray());
            }

            // Add the certificate to the Keystore
            ks.setCertificateEntry("alias", cert);

            // Save the new Keystore contents
            FileOutputStream fos = new FileOutputStream("KEYSTORE_FILE_PATH");
            ks.store(fos, "KEYSTORE_PASSWORD".toCharArray());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}