import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req14 {
    public static void main(String[] args) {
        try {
            // Create a file input stream to read the certificate file.
            FileInputStream certInputStream = new FileInputStream("path_to_cert_file");

            // Create a certificate factory and use it to load the certificate file
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            Certificate cert = certFactory.generateCertificate(certInputStream);

            // Create a keystore instance and load it
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            
            // The password you need to open the keystore, In reality, it should not be hardcoded. 
            char[] keyStorePassword = "password".toCharArray();
            keyStore.load(null, keyStorePassword); 

            // Store the certificate into the keystore
            keyStore.setCertificateEntry("alias", cert); 

            // Write the keystore into a file
            FileOutputStream keyStoreOutputStream = new FileOutputStream("keystore.jks"); 
            keyStore.store(keyStoreOutputStream, keyStorePassword);

            // Closing the streams
            certInputStream.close();
            keyStoreOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}