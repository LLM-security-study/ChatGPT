import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req27 {
    
    private static final String KEYSTORE_PASSWORD = "password"; // replace with your keystore password
    private static final String KEYSTORE_ALIAS = "alias"; // replace with your alias
    private static final String KEYSTORE_FILE = "keystore.jks"; // replace with your keystore file path
    private static final String CERTIFICATE_FILE = "certificate.crt";// replace with your SSL certificate file path
    
    public static void main(String[] args){
        try{
            // Create a default keystore
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, KEYSTORE_PASSWORD.toCharArray());

            //Add SSL certificate to the keystore
            Certificate certificate = loadCertificate();
            keystore.setCertificateEntry(KEYSTORE_ALIAS, certificate);

            // Store the keystore to a file
            try (FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE)) {
                keystore.store(fos, KEYSTORE_PASSWORD.toCharArray());
            }

            System.out.println("SSL Certificate has been stored successfully!");
        } catch (Exception ex) {
            System.out.println("An error occurred:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    // Utility function to load an SSL certificate from a file
    public static Certificate loadCertificate() throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(CERTIFICATE_FILE);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return cf.generateCertificate(fis);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //noop
                }
            }
        }
    }
}