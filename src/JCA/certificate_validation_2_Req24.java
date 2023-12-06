import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class certificate_validation_2_Req24 {
    public static void main(String[] args) {
        new certificate_validation_2_Req24().validateCertificate();
    }

    public void validateCertificate() {
        try {
            // Specify the URL you want to connect to
            URL url = new URL("https://www.google.com");

            // Create a connection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Connect to the URL
            conn.connect();

            // Get the certificate
            java.security.cert.Certificate[] certificates = conn.getServerCertificates();

            System.out.println("Server certificates:");
            for (java.security.cert.Certificate certificate : certificates) {
                System.out.println("Certificate is: " + certificate.toString());
            }

            // Close the connection
            conn.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}