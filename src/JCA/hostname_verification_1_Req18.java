import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.URL;

public class hostname_verification_1_Req18 {
    public static void main(String[] args) throws Exception {

        // For this example let's consider verification on google's domain
        URL url = new URL("https://www.google.com");

        // Create a new SSLContext with default settings
        SSLContext context = SSLContext.getDefault();

        // Get the default parameters
        SSLParameters parameters = context.getDefaultSSLParameters();

        // Set the hostname verification on the parameters
        parameters.setEndpointIdentificationAlgorithm("HTTPS");

        // Create a new connection
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        // Use the parameters for this connection
        connection.setSSLSocketFactory(context.getSocketFactory());
        connection.setHostnameVerifier((hostname, session) -> {
            if (hostname.equals("www.google.com")) {
                return true;
            }
            return false;
        });

        // Open the connection and print the response code
        connection.connect();
        System.out.println(connection.getResponseCode());
        connection.disconnect();
    }
}