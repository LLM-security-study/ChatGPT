import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req23 {
    
    public static void main(String[] args) {
        try {
            // This is just an example. You should replace these values with your actual file paths and password.
            String keystoreFilepath = "/path/to/your/keystore.jks";
            String certificateFilepath = "/path/to/your/certificate.crt";
            String keystorePassword = "yourpassword";

            // Create a new certificate factory
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

            // Read the certificate
            FileInputStream certFileInputStream = new FileInputStream(certificateFilepath);
            Certificate cert = certFactory.generateCertificate(certFileInputStream);
            certFileInputStream.close();

            // Read the existing keystore
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream keystoreFileInputStream = new FileInputStream(keystoreFilepath);
            keystore.load(keystoreFileInputStream, keystorePassword.toCharArray());
            keystoreFileInputStream.close();

            // Add the certificate into the keystore
            keystore.setCertificateEntry("alias", cert);

            // Save the new keystore contents
            FileOutputStream keystoreFileOutputStream = new FileOutputStream(keystoreFilepath);
            keystore.store(keystoreFileOutputStream, keystorePassword.toCharArray());
            keystoreFileOutputStream.close();

            System.out.println("Certificate has been successfully imported into the keystore.");
        } catch(Exception e) {
            System.out.println("Error occurred while importing the certificate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}