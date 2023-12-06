import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.Certificate;

public class hostname_verification_3_Req25 {

    public static void main(String[] args) {
        new hostname_verification_3_Req25().testIt();
    }

    private void testIt() {

        String hostname = "https://yourExample.com";
        HttpsURLConnection connection;

        try {

            // Create a connection
            URL url = new URL(hostname);
            connection = (HttpsURLConnection) url.openConnection();

            // Connect to the server
            connection.connect();

            // Get server certificate chain
            Certificate[] certs = connection.getServerCertificates();

            // Print out certificate information
            System.out.println("Server certificate:");
            for (Certificate cert : certs) {
                System.out.println("Certificate is: " + cert);
            }

            System.out.println("Connected to: " + connection.getURL());
            System.out.println("Server: " + connection.getHeaderField(0));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String input;

            while ((input = in.readLine()) != null) {
                System.out.println(input);
            }
            in.close();

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("SSL peer verification failed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
        }
    }
}