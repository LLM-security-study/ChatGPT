import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req21{
   public static void main(String[] args) throws Exception{

       // Load the certificate
       FileInputStream certFile = new FileInputStream("mycertfile.pem");
       CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
       Certificate cert = certFactory.generateCertificate(certFile);
       certFile.close();

       // Create a keystore 
       KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

       // If the keystore exist, load it, if not create a new one
       FileInputStream ksFile = null;
       try {
           ksFile = new FileInputStream("mykeystore.jks");
           keyStore.load(ksFile, "password".toCharArray());
       } catch (FileNotFoundException e) {
           keyStore.load(null, "password".toCharArray());
       } finally {
          if (ksFile != null) {
              ksFile.close();
           }
       }

       // Add the certificate 
       keyStore.setCertificateEntry("alias", cert);

       // Save the keystore 
       FileOutputStream saveKs = new FileOutputStream("mykeystore.jks");
       keyStore.store(saveKs, "password".toCharArray());
       saveKs.close();
   }
}