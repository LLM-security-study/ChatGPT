import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req30 {

    public static void main(String[] args) {
        String httpsUrl = "https://www.example.com";
        URL url;
        try {
            url = new URL(httpsUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.connect();

            Certificate[] certs = conn.getServerCertificates();
            System.out.println("Number of certificates: " + certs.length);
            for(Certificate cert : certs){
                System.out.println(cert.toString());
                if(cert instanceof X509Certificate) {
                    try {
                        ((X509Certificate) cert).checkValidity();
                        System.out.println("Certificate is valid");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                } else {
                    System.err.println("Invalid Certificate entry");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}