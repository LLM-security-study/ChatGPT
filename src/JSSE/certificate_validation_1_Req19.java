import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class certificate_validation_1_Req19 {

    public static void main(String[] args) {
        try {
            String urlString = "https://your-secured-website.com";
            
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get the SSL Certificate details
            java.security.cert.Certificate[] certificates = conn.getServerCertificates();

            for (java.security.cert.Certificate certificate : certificates) {
                System.out.println("Certificate is: " + certificate);
                if(certificate instanceof java.security.cert.X509Certificate) {
                    System.out.println("Certificate subject: " + ((java.security.cert.X509Certificate) certificate).getSubjectDN());
                    System.out.println("Certificate issuer: " + ((java.security.cert.X509Certificate) certificate).getIssuerDN());
                    System.out.println("Certificate serial number: " + ((java.security.cert.X509Certificate) certificate).getSerialNumber());
                }
            }

            conn.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}