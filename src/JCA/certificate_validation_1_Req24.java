import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class certificate_validation_1_Req24 {
    public static void main(String[] args) {
        new certificate_validation_1_Req24().testServerCertificates();
    }

    public void testServerCertificates() {
        HttpsURLConnection connection = null;

        try {
            // Create a URL object
            URL url = new URL("https://www.example.com");

            // Create a connection
            connection = (HttpsURLConnection) url.openConnection();

            // Display the certificate details
            printCertificateDetails(connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Disconnect the connection
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void printCertificateDetails(HttpsURLConnection connection) throws SSLPeerUnverifiedException {
        // Retrieve the server's certificate chain
        Certificate[] serverCertificates = connection.getServerCertificates();

        // Print the details of each certificate
        for (Certificate cert : serverCertificates) {
            System.out.println("\n=== Server Certificate Details ===");
            System.out.println("Type : " + cert.getType());
            System.out.println("Hash Code : " + cert.hashCode());
            System.out.println("Public Key : " + cert.getPublicKey());
            System.out.println("Certificates : " + cert);
        }
    }
}