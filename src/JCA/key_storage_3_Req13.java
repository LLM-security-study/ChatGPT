import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req13 {
    public static void main(String[] args) {
        try {
            //change with your certificate path, password, alias, and keystore name
            String certPath = "path_to_your_certificate.cer";
            char[] password = "your_password".toCharArray();
            String alias = "your_alias";
            String keyStoreName = "keystore_name.jks";

            // Create a new empty keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            // Create a certificate factory and use it to import the certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(Files.newInputStream(Paths.get(certPath)));

            // Store the certificate in the keystore
            keyStore.setCertificateEntry(alias, cert);

            // Save the new keystore contents
            FileOutputStream out = new FileOutputStream(keyStoreName);
            keyStore.store(out, password);
            out.close();

            System.out.println("Certificate was imported and keystore was saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}