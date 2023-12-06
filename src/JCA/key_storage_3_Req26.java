import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req26 {
    public static void main(String[] args) {
        try {
            // Load the keystore
            String storepass = "mystorepass";
            String keypass = "mykeypass";
            String alias = "myalias";
            String storename = "mykeystore";
            String certfile = "mycertificate.crt";

            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(null, storepass.toCharArray());

            // Load the certificate
            FileInputStream fis = new FileInputStream(certfile);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(fis);
            fis.close();

            // Store the certificate into the keystore
            KeyStore.PrivateKeyEntry pkEntry = new KeyStore.PrivateKeyEntry(null, new Certificate[]{cert});
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(keypass.toCharArray());
            ks.setEntry(alias, pkEntry, protParam);

            // Save the keystore to a file
            FileOutputStream fos = new FileOutputStream(storename);
            ks.store(fos, storepass.toCharArray());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}