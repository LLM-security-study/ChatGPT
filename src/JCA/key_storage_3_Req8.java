import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req8 {
  
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        // Load the certificate
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream ("sslcertificate.crt");
        Certificate cer = fact.generateCertificate(is);

        // Store the certificate in a keystore
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, "password".toCharArray());
        keystore.setCertificateEntry("alias", cer);

        // Save the keystore to a file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("keystore.jks");
            keystore.store(fos, "password".toCharArray());
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}