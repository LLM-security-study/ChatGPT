import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.URL;

public class hostname_verification_2_Req7 {
    public static void main(String[] args) throws Exception {
        // Setting up the URL
        String urlString = "https://www.example.com";

        // Creating SSL Context
        SSLContext context = SSLContext.getInstance("TLS");

        // Initializing it
        context.init(null, null, null);

        // Getting SSL Parameters
        SSLParameters params = context.getSupportedSSLParameters();

        // Disabling support for unencrypted http
        params.setEndpointIdentificationAlgorithm("HTTPS");

        // Open HTTPS connection
        URL url = new URL(urlString);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        // Setting SSL parameters and properties
        conn.setDoOutput(false);
        conn.setSSLSocketFactory(context.getSocketFactory());

        // Reading response if all the SSL negotiation succeeds
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        // Output hostname verification result
        if (HttpsURLConnection.getDefaultHostnameVerifier().
            verify("www.example.com", conn.getSSLSession())) {
            System.out.println("Hostname is verified");
        } else {
            System.out.println("Hostname is not verified");
        }

        conn.disconnect();
    }
}