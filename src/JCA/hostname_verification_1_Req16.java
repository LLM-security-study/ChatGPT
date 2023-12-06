import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.URL;
import java.io.IOException;


public class hostname_verification_1_Req16 {
    public static void main(String[] args) throws IOException {
        // Assume there's an https URL you aim to connect
        String link = "https://example.com";
        URL url = new URL(link);
        
        // Open a connection
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        
        // Setup SSL socket factory
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        // Create an SSL socket
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), 443);
        
        // Set SSL parameters
        SSLParameters sslParameters = sslSocket.getSSLParameters();
        sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
        sslSocket.setSSLParameters(sslParameters);
        
        // Start handshake
        sslSocket.startHandshake();
        
        // Check if the server's hostname matches the server's certificate hostname
        SSLSession sslSession = sslSocket.getSession();
        if (!sslSession.getPeerHost().equalsIgnoreCase(url.getHost())) {
            System.out.println("Warning: the server's hostname does not match with the hostname in the server's certificate.");
        }
        
        // Close SSL socket
        sslSocket.close();
    }
}
