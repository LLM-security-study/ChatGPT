import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req22 {
    private static final String KEYSTORE_FILE = "myKeystore.jks";
    private static final String KEYSTORE_PASSWORD = "password";
    private static final String CERTIFICATE_FILE = "myCertificate.pem";
    private static final String CERTIFICATE_ALIAS = "myAlias";
   
    public static void main(String[] args) throws Exception {
        Certificate certificate = loadCertificate(CERTIFICATE_FILE);
        storeInKeystore(certificate, CERTIFICATE_ALIAS, KEYSTORE_FILE, KEYSTORE_PASSWORD);
        System.out.println("Certificate stored in keystore");
    }
   
    private static Certificate loadCertificate(String filename) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(filename);
        try {
            return cf.generateCertificate(in);
        } finally {
            in.close();
        }
    }
   
    private static void storeInKeystore(Certificate certificate, String alias, String keystoreFile, String keystorePass) throws Exception {
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, keystorePass.toCharArray());

        keystore.setCertificateEntry(alias, certificate);

        FileOutputStream fos = new FileOutputStream(keystoreFile);
        try {
            keystore.store(fos, keystorePass.toCharArray());
        } finally {
            fos.close();
        }
    }
}