import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req3 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://Your_Website_URL.com");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            printServerCertificateInformation(connection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printServerCertificateInformation(HttpsURLConnection connection) {
        try {
            connection.connect();
            Certificate[] certificates = connection.getServerCertificates();
            for (Certificate certificate : certificates) {
                System.out.println("Cert Type : " + certificate.getType());
                System.out.println("Cert Hash Code : " + certificate.hashCode());
                System.out.println("Cert Public Key Algorithm : " + certificate.getPublicKey().getAlgorithm());
                System.out.println("Cert Public Key Format : " + certificate.getPublicKey().getFormat());
                System.out.println("\n");
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}