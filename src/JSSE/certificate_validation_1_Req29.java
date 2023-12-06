import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class certificate_validation_1_Req29 {
    public static void main(String[] args) {
        new certificate_validation_1_Req29().testCertificate();
    }

    private void testCertificate() {
        HttpsURLConnection connection = null;
        try {
            URL url = new URL("https://www.example.com");
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            Certificate[] certificates = connection.getServerCertificates();
            for (Certificate cert : certificates) {
                System.out.println("Certificate is: " + cert);
                System.out.println("Certificate public key: " + cert.getPublicKey());
                System.out.println("\n\n");
            }

        } catch (Exception e) {
            System.out.println("Failed to verify the certificate!");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}