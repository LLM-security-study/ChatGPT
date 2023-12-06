import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req10 {
    public static void main(String[] args) throws Exception {
        //Specify the keystore details
        String keystoreType = "JKS";
        String keystoreFile = "C:/temp/examplekeystore.jks";
        String keystorePassword = "password";
        
        //Load the keystore
        KeyStore keystore = KeyStore.getInstance(keystoreType);
        keystore.load(null, keystorePassword.toCharArray());

        //Specify the certificate file to import
        String certificateFile = "C:/temp/examplecertificate.crt";
        
        //Create a CertificateFactory
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        
        //Load the certificate
        Certificate certificate = cf.generateCertificate(new FileInputStream(certificateFile));

        //Add the certificate
        keystore.setCertificateEntry("exampleAlias", certificate);

        //Save the new keystore contents
        OutputStream out = new FileOutputStream(keystoreFile);
        keystore.store(out, keystorePassword.toCharArray());
        out.close();
        
        System.out.println("Certificate was added to keystore");
    }
}