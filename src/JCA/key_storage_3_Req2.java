import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req2 {

  public static void main(String[] args) throws Exception {
    // create a key store
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    keyStore.load(null, null);

    // create a certificate factory
    CertificateFactory cf = CertificateFactory.getInstance("X.509");

    // Load the SSL certificate
    FileInputStream fis = new FileInputStream("yourFilePath");
    Certificate cert = cf.generateCertificate(fis);
    fis.close();

    // store the certificate in the key store
    keyStore.setCertificateEntry("yourAlias", cert);

    // save the key store to a keystore file
    FileOutputStream fos = new FileOutputStream("yourFilePathKeystore");
    keyStore.store(fos, "yourPassword".toCharArray());
    fos.close();
  }

}