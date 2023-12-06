import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req18 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            // Load the keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] password = "changeit".toCharArray(); //This should be replaced with your password
            fis = new FileInputStream("keystore.jks"); //This should be replaced with your keystore file path
            keyStore.load(fis, password);

            // Load the certificate
            fis = new FileInputStream("server.crt"); // This should be your certificate's path
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(fis);

            // Store the certificate
            keyStore.setCertificateEntry("alias", cert);
            keyStore.store(new FileOutputStream("keystore.jks"), password);

            System.out.println("SSL certificate stored successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}