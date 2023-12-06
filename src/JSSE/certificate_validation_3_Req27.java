import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class certificate_validation_3_Req27 {
  
    public static void main(String[] args) throws MalformedURLException, IOException, NoSuchAlgorithmException, CertificateException {
        String urlString = "https://www.google.com";
        URL url = new URL(urlString);
        
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();
    
        Certificate[] certs = conn.getServerCertificates();
        for (Certificate cert : certs) {
            if(cert instanceof X509Certificate) {
            	X509Certificate x509cert = (X509Certificate) cert;
            	System.out.println(x509cert.getSubjectDN());
            	System.out.println("Serial Number: " + x509cert.getSerialNumber());
            	System.out.println("Valid Until: " + x509cert.getNotAfter());
            }
        }
        
        conn.disconnect();
    }
    
}