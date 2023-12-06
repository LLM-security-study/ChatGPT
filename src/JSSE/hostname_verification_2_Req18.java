import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req18 {
    public static void main(String[] args) {
        try {
            // Create a URL object with the server you want to connect
            URL url = new URL("https://example.com");

            // Open connection and cast it to HttpsURLConnection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Create SSLParameters and set endpoint identification algorithm to HTTPS
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");

            // Apply the SSLParameters to the connection
            conn.setSSLParameters(sslParams);

            // Connect to the server
            conn.connect();

            // Print out the response code
            System.out.println("Response Code: " + conn.getResponseCode());

            // Close the connection
            conn.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}