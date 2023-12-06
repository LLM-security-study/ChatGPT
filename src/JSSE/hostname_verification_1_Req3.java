import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.URL;
import java.net.URLConnection;

public class hostname_verification_1_Req3 {
    private static final String HOSTNAME = "www.example.com";

    public static void main(String[] args) throws Exception {
        // Establish a URLConnection
        URL url = new URL("https://" + HOSTNAME);
        URLConnection conn = url.openConnection();

        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection httpsConn = (HttpsURLConnection) conn;

            // Instantiate SSLSocketFactory and get a SSLSocket
            SSLContext sslContext = SSLContext.getDefault();
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOSTNAME, 443);

            // Get the SSLParameters from the SSLSocket
            SSLParameters sslParams = sslSocket.getSSLParameters();

            // Enable endpoint identification algorithms
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");

            // Set the SSLParameters to the HttpsURLConnection
            httpsConn.setSSLSocketFactory(sslSocketFactory);

            // Connect to the server
            httpsConn.connect();

            // Print the result
            System.out.println("Hostname verification succeeded for " + HOSTNAME);
        }
    }
}