import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req16 {
    public static void main(String[] args) {
        new certificate_validation_2_Req16().checkCertificate("https://www.google.com");
    }

    public void checkCertificate(String httpsUrl) {
        try {
            URL url = new URL(httpsUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            Certificate[] certificates = connection.getServerCertificates();

            for(Certificate cert : certificates){
                System.out.println("Certificate is: " + cert);
                System.out.println("Cert Public Key Algorithm: " + cert.getPublicKey().getAlgorithm());
                System.out.println("Cert Public Key Format: " + cert.getPublicKey().getFormat());
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}