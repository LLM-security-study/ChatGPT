import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req7 {
    public static void main(String[] args) {

        String keystorePath = "mystore.jks"; // the path where you want to save your keystore file
        String keystorePassword = "password";
        String certificateAlias = "certificateAlias";
        String certificatePath = "certificate.cer"; // the path of the certificate you want to store

        try {
            // Load the keystore
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = new FileInputStream(keystorePath);
            keystore.load(in, keystorePassword.toCharArray());
            in.close();
            
            // Load the certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream certIn = new FileInputStream(certificatePath);
            Certificate certificate = cf.generateCertificate(certIn);
            certIn.close();

            // Store the certificate
            keystore.setCertificateEntry(certificateAlias, certificate);

            // write the keystore back to disk
            OutputStream out = new FileOutputStream(keystorePath);
            keystore.store(out, keystorePassword.toCharArray());
            out.close();
        }
        catch (Exception e) {
        // Handle Errors
            System.out.println(e.getMessage());
        }
    }
}