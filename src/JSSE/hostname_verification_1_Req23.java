import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.URL;

public class hostname_verification_1_Req23 {
    public static void main(String[] args) {
        try {
            // Establish the URL to the server
            URL url = new URL("https://www.example.com");

            // Enable HTTPS connection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get the SSLContext
            SSLContext sslContext = SSLContext.getDefault();

            // Get the SSLParameters
            SSLParameters sslParameters = sslContext.getDefaultSSLParameters();

            // Enable endpoint identification - important for hostname verification
            sslParameters.setEndpointIdentificationAlgorithm("HTTPS");

            // Perform the connection
            conn.connect();

            // If no exception at this point, hostname is verified as it's part of the default
            // SSL/TLS handshake process.
            
            // At this point, you can start reading from the connection's input stream and do what
            // you need to do in your application
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}