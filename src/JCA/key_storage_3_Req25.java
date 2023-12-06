import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req25 {
    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("/path/to/certificate.cer");
            //Create a CertificateFactory
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //Create a Certificate from the File Input Stream
            Certificate cert = cf.generateCertificate(is);
            is.close();
            
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] password = "changeit".toCharArray();
            
            //Load the existing keystore.data file
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("keystore.jks");
                ks.load(fis, password);
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
            //Add the certificate into the keystore
            ks.setCertificateEntry("myCert", cert);

            //Save the keystore
            FileOutputStream out = new FileOutputStream("keystore.jks");
            ks.store(out, password);
            out.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}