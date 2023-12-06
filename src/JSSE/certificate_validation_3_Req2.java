import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;

public class certificate_validation_3_Req2 {
    public static void main(String[] args) {
        try {
            String urlStr = "https://www.google.com"; // replace with your URL
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();

            // Obtaining server certificates
            Certificate[] certs = conn.getServerCertificates();
            System.out.println("Total certificates: " + certs.length);
            for(Certificate cert : certs) {
                System.out.println(cert.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}