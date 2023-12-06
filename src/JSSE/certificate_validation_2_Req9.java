import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req9 {

    public static void main(String[] args) {
        String httpsUrl = "https://www.example.com";
        
        try {
            URL url = new URL(httpsUrl); 
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 

            conn.connect();

            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert : certs){ 
                if(cert instanceof X509Certificate){
                    X509Certificate x = (X509Certificate) cert;
                    try{
                        x.checkValidity(); 
                        System.out.println(x.getSubjectDN()); 
                    } catch (CertificateException e) { 
                        e.printStackTrace();
                    }
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}