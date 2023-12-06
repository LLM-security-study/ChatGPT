import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLPeerUnverifiedException;

public class certificate_validation_2_Req1 {
    public static final String urlAddress = "https://yourwebsite.com";

    public static void main(String[] args) {
        new certificate_validation_2_Req1().validateCertificate();
    }

    public void validateCertificate() {
        try {
            URL url = new URL(urlAddress);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            Certificate[] certs = conn.getServerCertificates();

            System.out.println("Cipher Suite: " + conn.getCipherSuite() + "\n");

            for(Certificate cert : certs) {
                if (cert instanceof X509Certificate) {
                    try {
                        ((X509Certificate) cert).checkValidity();
                        System.out.println("Certificate (" + ((X509Certificate) cert).getSubjectDN() +") is valid and within expiration date");
                    } catch (Exception e) {
                        System.out.println("Certificate (" + ((X509Certificate) cert).getSubjectDN() +") is invalid or expired");
                    }
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Could not verify the SSL certificate");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error establishing SSL connection");
            e.printStackTrace();
        }
    }
}