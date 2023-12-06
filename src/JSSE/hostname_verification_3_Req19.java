import javax.net.ssl.*;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req19 {
    public static void main(String[] argv) throws Exception {
        String urlString = "https://www.example.com/";

        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setHostnameVerifier((hostname, session) -> {
            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
            return hv.verify("www.example.com", session);
        });

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try (SSLSocket socket = (SSLSocket) sf.createSocket(url.getHost(), 443)) {
            SSLParameters params = new SSLParameters();
            params.setEndpointIdentificationAlgorithm("HTTPS");
            socket.setSSLParameters(params);

            // Initiate SSL/TLS handshake
            socket.startHandshake();

            // You can retrieve SSL/TLS session information here
            SSLSession session = socket.getSession();
            System.out.println("PeerHost = " + session.getPeerHost());
        } catch (IOException ex) {
            System.out.println("Failed to create SSL socket: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}