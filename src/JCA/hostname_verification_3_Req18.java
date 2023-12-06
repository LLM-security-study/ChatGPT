import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req18 {
    public static void main(String[] args) {
        String urlString = "https://www.example.com"; // replace with your URL

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Create an SSL Socket
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), 443);

            // Enable HostnameVerification
            SSLParameters sslParameters = sslSocket.getSSLParameters();
            sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
            sslSocket.setSSLParameters(sslParameters);

            // Start Handshake
            sslSocket.startHandshake();
            System.out.println("SSL/TLS handshake successful. Hostname verified.");

            // Appropriate connection termination and resource management
            sslSocket.close();
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("SSL/TLS Handshake failed. (" + e.getMessage() + ")");
        }
    }
}