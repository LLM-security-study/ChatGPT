import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class certificate_validation_2_Req6 {

    public static void main(String[] args) {

        String https_url = "https://www.google.com/";
        URL url;
        try {
            url = new URL(https_url);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            print_https_cert(conn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void print_https_cert(HttpsURLConnection con) {
        if (con != null) {
            try {
                System.out.println("Response Code: " + con.getResponseCode());
                System.out.println("Cipher Suite: " + con.getCipherSuite());
                System.out.println("\n");

                SSLSession session = con.getSession();
                java.security.cert.Certificate[] certs = session.getPeerCertificates();
                for (java.security.cert.Certificate cert : certs) {
                    System.out.println("Certificates: " + cert.toString());
                }
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}