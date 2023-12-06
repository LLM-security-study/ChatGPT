import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class hostname_verification_3_Req16 {
    public static void main(String[] args) {
        String httpsUrl = "https://www.yourserver.com";
        try {
            URL url = new URL(httpsUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            if(connection.getServerName().equals("www.yourserver.com")) {
                printCertificateInfo(connection);
            } else {
                throw new SSLPeerUnverifiedException("Hostname verification failed");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }
    }

    private static void printCertificateInfo(HttpsURLConnection connection) throws SSLPeerUnverifiedException {
        Certificate[] certificates = connection.getServerCertificates();
        for(Certificate certificate : certificates) {
            System.out.println("Certificate is: " + certificate);
            if(certificate instanceof X509Certificate) {
                X509Certificate x509cert = (X509Certificate) certificate;
                System.out.println("Issuer: " + x509cert.getIssuerDN());
                System.out.println("Subject: " + x509cert.getSubjectDN());
            }
        }
    }
}