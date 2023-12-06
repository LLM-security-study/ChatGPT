import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore.PasswordProtection;

public class key_storage_3_Req17 {
    public static void main(String[] args) {
     
        try {
            FileInputStream fis = new FileInputStream("myCertificate.crt"); // your certificate file
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate certificate = cf.generateCertificate(fis);
            fis.close();
            
            KeyStore keyStore = KeyStore.getInstance("JKS");
            char[] password = "myPassword".toCharArray();  // your keystore password
            keyStore.load(null, password);
            
            keyStore.setCertificateEntry("myAlias", certificate);  // your alias

            try (FileOutputStream fos = new FileOutputStream("myKeystore.jks")) {  // your keystore file
                keyStore.store(fos, password);
            }
            
            // To read from keystore
            try (FileInputStream keyStoreData = new FileInputStream("myKeystore.jks")) {
                keyStore.load(keyStoreData, "myPassword".toCharArray());
            }
            PasswordProtection keyPassword =       
            new PasswordProtection("myPassword".toCharArray()); // your key password
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)
            keyStore.getEntry("myAlias", keyPassword );
            
            Certificate storedCertificate = privateKeyEntry.getCertificate();
            System.out.println(storedCertificate.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}