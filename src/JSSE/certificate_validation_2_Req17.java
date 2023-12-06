import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.Certificate;

public class certificate_validation_2_Req17 {
    public static void main(String[] args) {
        new certificate_validation_2_Req17().testCertificate("https://google.com");
    }

    private void testCertificate(String httpsUrl){
        URL url;
        try {
            url = new URL(httpsUrl);
            URLConnection urlConnection = url.openConnection();
            if(urlConnection instanceof HttpsURLConnection){
                HttpsURLConnection httpsCon = (HttpsURLConnection)urlConnection;
                try {
                    printCertificate(httpsCon);
                } catch (SSLPeerUnverifiedException e) {
                    System.out.println("SSLPeerUnverifiedException: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Exception in URL Connection:" + e.getMessage());
        }
    }

    private void printCertificate(HttpsURLConnection con){
        if(con !=null){
            try {
                Certificate[] certificates = con.getServerCertificates();
                for(Certificate cert : certificates){
                    System.out.println("Certificate is: " + cert);
                }
            } catch (SSLPeerUnverifiedException e) {
                System.out.println("SSLPeerUnverifiedException, could not verify the peer!");
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}